package com.practice.security.configure;

import com.practice.security.dto.DummyUserDTO;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    // HTTP 요청과 응답에 대해 Security적 Configuration을 진행함
    // Spring 4, 5, 6 버전에 따라 구성이 조금씩 다름
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
//        http.csrf(configure -> {
//            configure.disable(); // csrf 끈다
//        });

        http.authorizeHttpRequests(configure -> {
            configure.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            // ant Matcher를 기본으로 사용한다  ( /security/read/ 나 /security/read 를 전부 authenticated )
            configure.requestMatchers("/security/read/**").hasAnyAuthority("READ", "WRITE"); // READ권한 있는 유저만 가능!
            configure.requestMatchers(HttpMethod.DELETE, "/security/delete").hasAuthority("DELETE");
            configure.requestMatchers(HttpMethod.GET, "/security/delete").hasRole("ADMIN");
            // 정규식 Matcher를 사용하고 싶을 때
//            configure.requestMatchers(RegexRequestMatcher.regexMatcher("/security*/**")).authenticated();
            // mvc Matcher를 사용하고 싶을 때 ( /security/read/ 나 /security/read 를 전부 authenticated )
//            configure.requestMatchers(mvc.pattern("/security/read/**")).authenticated();
            // dispathcer type Matcher를 사용하고 싶을 때
//            configure.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll();

            configure.anyRequest().permitAll();
        });

        // 나는 로그인 할 때 form을 통한 로그인을 사용하겠습니다
        http.formLogin(configure -> {
            // 우리가 사용하는 폼 로그인 화면은 /security/login 이라는 GET요청 경로다!
            // 이 페이지는 인증 없이 갈 수 있도록 하겠다
            configure.loginPage("/security/login")
                    // 실제 POST를 보내는 (Spring Security의 UserDetailsService의 loadUserbyusername을 실행시키는) 경로
                    // => 쉽게말해서 로그인 페이지에서 action에 적어야 하는 거 설정
                    .loginProcessingUrl("/security/login")
                    // loadUserbyusername에 username 파라미터에 보내고 싶은 값에 대한 form의 name 설정 (아이디)
                    .usernameParameter("username")
                    // spring security에 보내고 싶은 Credential 값에 대한 form의 name 설정 (비밀번호)
                    .passwordParameter("password")
                    .permitAll();
        });

        http.logout(configure -> {
            configure.logoutUrl("/security/logout").permitAll();
            configure.invalidateHttpSession(true);
            configure.deleteCookies("JSESSIONID");
            configure.clearAuthentication(true);
            configure.logoutSuccessUrl("/");
        });

        http.rememberMe(configure -> {
            configure.tokenValiditySeconds(60);
            configure.rememberMeParameter("save-user");
        });



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
