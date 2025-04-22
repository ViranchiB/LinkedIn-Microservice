package com.linkedin.Post_Service.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public PostLike() {
    }

    public PostLike(Long likeId, Long postId, Long userId, LocalDateTime createdAt) {
        this.likeId = likeId;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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
