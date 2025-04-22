package com.linkedin.Post_Service.Controller;

import com.linkedin.Post_Service.Service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikesController {

    private final PostLikeService postLikeService;

    public LikesController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        this.postLikeService.likePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        this.postLikeService.unlikePost(postId, 1L);
        return ResponseEntity.noContent().build();
    }

}
