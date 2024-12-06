package com.practice.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class DummyUserDTO implements UserDetails {
    private String id;
    private String password;
    private String name;
    private Integer age;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("WRITE"),
                new SimpleGrantedAuthority("READ"),
                new SimpleGrantedAuthority("ROLE_NORMAL") // ADMIN이라고하는 역할
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }
}
