package com.linkedin.Post_Service.Service.Impl;

import com.linkedin.Post_Service.DTO.PostCreateRequestDto;
import com.linkedin.Post_Service.DTO.PostDto;
import com.linkedin.Post_Service.Entity.Post;
import com.linkedin.Post_Service.Exceptions.ResourceNotFoundException;
import com.linkedin.Post_Service.Service.PostService;
import com.linkedin.Post_Service.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(Long userId, PostCreateRequestDto postCreateRequestDto) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        Post savePost = this.postRepository.save(post);
        return modelMapper.map(savePost, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        log.debug("Retrieving the Post by ID : {} ", postId);
        Post post = this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post not found with ID : " + postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> allPosts = this.postRepository.findByUserId(userId);

        // Convert all list of post to postDto (Every Post is a separate entity)
        return allPosts
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }
}
