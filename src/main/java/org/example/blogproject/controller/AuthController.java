package org.example.blogproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.blogproject.data.entity.User;
import org.example.blogproject.data.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // ✅ 비밀번호 검증
                session.setAttribute("user", user); // ✅ 세션에 사용자 정보 저장
                return "로그인 성공!";
            }
        }
        return "로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // ✅ 세션 삭제
        return "로그아웃 성공!";
    }
}
