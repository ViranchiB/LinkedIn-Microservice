package com.linkedin.Post_Service.Service;

import com.linkedin.Post_Service.DTO.PostCreateRequestDto;
import com.linkedin.Post_Service.DTO.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(Long userId, PostCreateRequestDto postCreateRequestDto);

    PostDto getPostById(Long postId);
    List<PostDto> getAllPostsOfUser(Long userId);
}
