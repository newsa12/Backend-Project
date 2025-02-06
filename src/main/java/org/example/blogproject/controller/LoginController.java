package org.example.blogproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login.html"; // templates/login.html
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup.html"; // templates/signup.html
    }
}
