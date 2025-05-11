package com.linkedin.Post_Service.Controller;

import com.linkedin.Post_Service.Config.Auth.UserContextHolder;
import com.linkedin.Post_Service.DTO.PostCreateRequestDto;
import com.linkedin.Post_Service.DTO.PostDto;
import com.linkedin.Post_Service.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto PostCreateRequestDto) {
        return new ResponseEntity<>(this.postService.createPost(PostCreateRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Long postId) {
        return new ResponseEntity<>(this.postService.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/users/allPostsByUserId")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(this.postService.getAllPostsOfUser(), HttpStatus.OK);
    }
}
