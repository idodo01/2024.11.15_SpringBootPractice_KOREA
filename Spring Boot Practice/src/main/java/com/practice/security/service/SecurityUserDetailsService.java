package com.practice.security.service;

import com.practice.security.dto.DummyUserDTO;
import com.practice.security.mapper.SecurityUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// 2.
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    // 3)
    @Autowired
    private PasswordEncoder passwordEncoder;
    // 4)
    @Autowired
    SecurityUserMapper securityUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1)
//        System.out.println("[loadUserByUsername]이 실행되었다. username: " + username);
//        DummyUserDTO userDTO = new DummyUserDTO();
//
//        // user 123으로 입력해야지 자격증명 통과됨
//        userDTO.setId("user");
//        userDTO.setPassword("123");
//        return userDTO;

        // 2)
//        System.out.println("[loadUserByUsername]이 실행되었다. username: " + username);
//        DummyUserDTO userDTO = new DummyUserDTO();
//
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodingPw = passwordEncoder.encode("123");
//        System.out.println("인코딩된 패스워드(123): " + encodingPw);
//        boolean result = passwordEncoder.matches("123", encodingPw);
//        System.out.println("123과 인코딩패스워드의 비교 결과: " + result);
//
//        userDTO.setId("user");
//        userDTO.setPassword(encodingPw);
//        return userDTO;

        // 3)     + mapper 추가 했음
        System.out.println("[loadUserByUsername]이 실행되었다. username: " + username);
        // 해당 아이디를 가지는 유저를 찾기! (DB에서)
        DummyUserDTO user = securityUserMapper.getUserById(username);
        // DB에 해당 아이디의 유저가 없다? 로그인 실패!
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        // DB에 해당 아이디의 유저가 있다? 그 유저가 가지는 패스워드와 사용자가 입력한 패스워드 비교해줘!
        return user;


    }
}
