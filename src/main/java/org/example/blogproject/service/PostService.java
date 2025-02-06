package org.example.blogproject.service;

import lombok.RequiredArgsConstructor;
import org.example.blogproject.data.entity.Post;
import org.example.blogproject.data.repository.PostRepository;
import org.example.blogproject.dto.PostRequestDto;
import org.example.blogproject.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 모든 게시글 조회
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 게시글 조회
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return new PostResponseDto(post);
    }

    // 게시글 작성 (로그인 없이 가능)
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post();
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        Post savedPost = postRepository.save(post);
        return new PostResponseDto(savedPost);
    }

    // 게시글 수정 (작성자 확인 없이 수정 가능)
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        Post updatedPost = postRepository.save(post);
        return new PostResponseDto(updatedPost);
    }

    // 게시글 삭제 (작성자 확인 없이 삭제 가능)
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        postRepository.delete(post);
    }
}
