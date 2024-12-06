package com.practice.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @GetMapping("/login")
    public void login() {}
    @GetMapping("/logout")
    public void logout() {}
    @GetMapping("/read")
    public void read() {}
    @GetMapping("/write")
    public void write() {}
    @GetMapping("/delete")
    public void delete() {}
}
