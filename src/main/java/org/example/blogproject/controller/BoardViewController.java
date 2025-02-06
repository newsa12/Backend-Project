package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardViewController {

    private final PostService postService;

    // 게시판 HTML 페이지 반환
    @GetMapping
    public String boardPage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "board";  // board.html 렌더링
    }
}
