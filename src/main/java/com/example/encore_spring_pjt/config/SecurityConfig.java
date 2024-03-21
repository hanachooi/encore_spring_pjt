package com.example.encore_spring_pjt.config;

// 시큐리티 버전 3 을 기준으로 함.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // 씨큐리티의 기능들을 disable 시킴. -> 우리는 세션 방식으로 로그인을 구현햇기 때문임.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };



}
