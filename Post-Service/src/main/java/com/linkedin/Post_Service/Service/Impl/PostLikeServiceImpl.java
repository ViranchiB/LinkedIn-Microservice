package com.linkedin.Post_Service.Service.Impl;

import com.linkedin.Post_Service.Entity.PostLike;
import com.linkedin.Post_Service.Exceptions.BadRequestException;
import com.linkedin.Post_Service.Exceptions.ResourceNotFoundException;
import com.linkedin.Post_Service.Repository.PostLikeRepository;
import com.linkedin.Post_Service.Repository.PostRepository;
import com.linkedin.Post_Service.Service.PostLikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostLikeServiceImpl implements PostLikeService {

    private final Logger log = LoggerFactory.getLogger(PostLikeServiceImpl.class);

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public PostLikeServiceImpl(PostLikeRepository postLikeRepository, PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void likePost(Long postId, Long userId) {
        log.debug("Attempting to like post with id {} and user id {}", postId, userId);

        // Check if post exists or not
        boolean exists = this.postRepository.existsById(postId);
        if (!exists) {
            throw new ResourceNotFoundException("Post not found with ID : " + postId);
        }

        // Check if user already liked the post or not
        boolean alreadyLiked = this.postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (alreadyLiked) {
            throw new BadRequestException("You already liked the post : " + postId);
        }

        // Set user like on post
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        this.postLikeRepository.save(postLike);

        log.debug("Post successfully liked with userId {} and postId {}", userId, postId);
    }

    @Override
    public void unlikePost(Long postId, long userId) {
        log.debug("Attempting to unlike post with id {} and user id {}", postId, userId);

        // Check if post exists or not
        boolean exists = this.postRepository.existsById(postId);
        if (!exists) {
            throw new ResourceNotFoundException("Post not found with ID : " + postId);
        }

        // Check if user already liked the post or not
        boolean userLikedOrNot = this.postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if (!userLikedOrNot) {
            throw new BadRequestException("You can't unlike the post which isn't liked");
        }

        // Delete the entry of user, liked the post
        this.postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.debug("Post successfully unliked with userId {} and postId {}", userId, postId);
    }
}
