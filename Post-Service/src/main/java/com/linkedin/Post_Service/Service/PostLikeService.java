package com.linkedin.Post_Service.Service;

public interface PostLikeService {
    public void likePost(Long postId, Long userId);

    void unlikePost(Long postId, long userId);
}
