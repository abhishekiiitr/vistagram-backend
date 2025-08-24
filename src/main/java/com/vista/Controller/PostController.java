package com.vista.Controller;

import com.vista.entity.PostEntity;
import com.vista.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostEntity>> getAllPosts() {
        List<PostEntity> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable String id) {
        Optional<PostEntity> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/posts")
    public ResponseEntity<PostEntity> createPost(
            @RequestParam("username") String username,
            @RequestParam("caption") String caption,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            PostEntity createdPost = postService.createPost(username, caption, image);
            return ResponseEntity.ok(createdPost);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<PostEntity> toggleLike(
            @PathVariable String id,
            @RequestParam String userId) {

        PostEntity updatedPost = postService.toggleLike(id, userId);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/posts/{id}/share")
    public ResponseEntity<PostEntity> sharePost(@PathVariable String id) {
        PostEntity updatedPost = postService.incrementShare(id);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/posts/{id}/liked")
    public ResponseEntity<Boolean> isLikedByUser(
            @PathVariable String id,
            @RequestParam String userId) {

        boolean isLiked = postService.isLikedByUser(id, userId);
        return ResponseEntity.ok(isLiked);
    }
}