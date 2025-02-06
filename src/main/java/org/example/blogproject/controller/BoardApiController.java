package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.dto.PostRequestDto;
import org.example.blogproject.dto.PostResponseDto;
import org.example.blogproject.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final PostService postService;

    // 모든 게시글 조회 (JSON 반환)
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    // 게시글 작성 (로그인한 사용자만 가능)
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(403).build(); // 로그인하지 않은 경우 작성 불가
        }

        return ResponseEntity.ok(postService.createPost(requestDto));
    }

    // 게시글 삭제 (로그인한 사용자만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(403).build(); // 로그인하지 않은 경우 삭제 불가
        }

        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
