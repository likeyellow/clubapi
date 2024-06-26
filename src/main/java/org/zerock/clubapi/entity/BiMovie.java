package org.zerock.clubapi.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "posterList")
@Table(name = "tbl_bimovie")
public class BiMovie extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, 
                mappedBy = "bimovie",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Poster> posterList = new ArrayList<>();

    public void addPoster(Poster poster) {
        poster.setIdx(this.posterList.size());
        poster.setBimovie(this);
        posterList.add(poster);
    }

    public void removePoster(Long ino) {

        Optional<Poster> result = posterList.stream().filter(p -> p.getIno() == ino).findFirst();

        result.ifPresent(p -> {
            p.setBimovie(null);
            posterList.remove(p);
        });
        // 포스터 번호 변경
        changeIdx();
    }

    private void changeIdx() {
        
        for(int i = 0; i < posterList.size(); i++) {
            posterList.get(i).setIdx(i);
        }
    }
}
