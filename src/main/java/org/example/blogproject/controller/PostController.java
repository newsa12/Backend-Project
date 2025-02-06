package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.dto.PostRequestDto;
import org.example.blogproject.dto.PostResponseDto;
import org.example.blogproject.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts") // 기존 "/posts" -> "/api/posts" 변경
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 특정 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    // 게시글 작성
    @PostMapping("/new") // 기존 "/posts" -> "/api/posts/new" 변경
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.createPost(requestDto));
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return ResponseEntity.ok(postService.updatePost(id, requestDto));
    }
}
