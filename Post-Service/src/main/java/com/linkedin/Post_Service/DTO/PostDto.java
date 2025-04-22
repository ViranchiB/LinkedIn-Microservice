package com.linkedin.Post_Service.DTO;

import java.time.LocalDateTime;

public class PostDto {
    private Long postId;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

    public PostDto() {
    }

    public PostDto(Long postId, String content, Long userId, LocalDateTime createdAt) {
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
