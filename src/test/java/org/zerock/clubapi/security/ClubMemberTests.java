package org.zerock.clubapi.security;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.clubapi.entity.ClubMember;
import org.zerock.clubapi.entity.ClubMemberRole;
import org.zerock.clubapi.repository.ClubMemberRepository;

@SpringBootTest
public class ClubMemberTests {
    
    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {

        // 1 - 80 까지는 USER만
        // 81 - 90 까지는 USER, MEMBER
        // 91 - 100 까지는 USER, MANAGER, ADMIN

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                .email("user" + i + "@test.org")
                .name("사용자" +i)
                .fromSocial(false)
                .password(passwordEncoder.encode("1111"))
                .build();

            // default role
            clubMember.addMemberRole(ClubMemberRole.USER);  
            
            if(i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if(i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
            
            clubMemberRepository.save(clubMember);
        });
    }

    @Test
    public void testRead() {

        Optional<ClubMember> result = clubMemberRepository.findByEmail("user95@test.org", false);

        ClubMember clubMember = result.get();

        System.out.println(clubMember);

    }
}
