package com.practice.security.configure;

import com.practice.security.dto.DummyUserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    // 1.
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var manager = new InMemoryUserDetailsManager();
//        var user1 = User.withUsername("john").password("123").authorities("A").build();
//        var user2 = User.withUsername("james").password("123").authorities("B").build();
//        DummyUserDTO dummyUserDTO = new DummyUserDTO();
//        dummyUserDTO.setId("terry");
//        dummyUserDTO.setPassword("123");
//
//
//        manager.createUser(user1);
//        manager.createUser(user2);
//        manager.createUser(dummyUserDTO);
//        return manager;
//    }

    // 2.
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    // 3.      + controller 추가했음 + resource > templates > security > login.html 추가했음
    // HTTP 요청과 응답에 대해 Security적 Configuration을 진행함
    // Spring 4, 5, 6 버전에 따라 구성이 조금씩 다름
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configure -> {
            configure.anyRequest().authenticated();
        });
        // 나는 로그인 할 때 form을 통한 로그인을 사용하겠습니다
        http.formLogin(configure -> {
            // 우리가 사용하는 폼 로그인 화면은 /security/login 이라는 GET요청 경로다!
            // 이 페이지는 인증 없이 갈 수 있도록 하겠다
            configure.loginPage("/security/login")
                    // 실제 POST를 보내는 (Spring Security의 UserDetailsService의 loadUserbyusername을 실행시키는) 경로
                    // => 쉽게말해서 로그인 페이지에서 action에 적어야 하는 거 설정
                    .loginProcessingUrl("/security/login")
                    .usernameParameter("abc")
                    .permitAll();
        });
        return http.build();
    }




}
