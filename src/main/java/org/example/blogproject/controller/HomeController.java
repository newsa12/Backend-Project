package org.example.blogproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html 로 연결
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // templates/about.html
    }

    @GetMapping("/worship")
    public String worship() {
        return "worship"; // templates/worship.html
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // templates/contact.html
    }
//
//    @GetMapping("/board")
//    public String board() {
//        return "board"; // templates/board.html
//    }
}
