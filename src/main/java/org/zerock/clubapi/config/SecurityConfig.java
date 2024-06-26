package org.zerock.clubapi.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.clubapi.security.filter.ApiCheckFilter;
import org.zerock.clubapi.security.filter.ApiLoginFilter;
import org.zerock.clubapi.security.handler.ApiLoginFailHandler;
import org.zerock.clubapi.security.handler.ClubLoginSuccessHandler;
import org.zerock.clubapi.security.service.ClubUserDetailsService;
import org.zerock.clubapi.security.util.JWTUtil;

import lombok.extern.log4j.Log4j2;


@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private ClubUserDetailsService userDetailsService; // 주입

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests()
        //         .antMatchers("/sample/all").permitAll()
        //         .antMatchers("/sample/member").hasRole("USER");
        
        http.formLogin(); // 인가, 인증에 문제시 로그인 화면   
        http.csrf().disable();   
        http.logout();

        http.oauth2Login().successHandler(successHandler());

        // 7days
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);

        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class);
                
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
        apiLoginFilter.setAuthenticationManager(authenticationManager());

        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
        
        return apiLoginFilter;
    }

    @Bean
    public JWTUtil jwtUtil() {

        return new JWTUtil();
    }

    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/notes/**/*", jwtUtil());
    }
    
    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }

    // 더이상 사용하지 않는다
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    //     // 사용자 계정은 user1
    //     auth.inMemoryAuthentication().withUser("user1")
    //     // 1111 패스워드 인코딩 결과
    //     .password("$2a$10$m.tFf0rNSrRj76jmPqmY2Of0fEkykDYZWw/ScxbECA9hCVXfQrOSu")
    //     .roles("USER");
    // }




}
