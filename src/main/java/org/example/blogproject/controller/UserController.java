package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.dto.UserDto;
import org.example.blogproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // templates/register.html 로 이동
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto) {
        userService.registerUser(userDto);
        return "login"; // 회원가입 성공 후 홈페이지로 리다이렉트
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // templates/login.html 로 이동
    }
}