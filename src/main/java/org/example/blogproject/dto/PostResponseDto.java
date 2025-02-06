package org.example.blogproject.dto;

import lombok.Getter;
import org.example.blogproject.data.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;  // 작성자 추가
    private final LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor().getUsername();  // 유저네임 반환
        this.createdAt = post.getCreatedAt();
    }
}

