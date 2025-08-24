package com.vista.services;

import com.vista.entity.PostEntity;
import com.vista.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostEntity> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<PostEntity> getPostById(String id) {
        return postRepository.findById(id);
    }

    public PostEntity createPost(String username, String caption, MultipartFile image) throws Exception {
        // Convert image to base64
        String imageBase64 = null;
        if (image != null && !image.isEmpty()) {
            byte[] imageBytes = image.getBytes();
            imageBase64 = "data:" + image.getContentType() + ";base64," + Base64.getEncoder().encodeToString(imageBytes);
        }

        PostEntity post = PostEntity.builder()
                .username(username)
                .caption(caption)
                .imageBase64(imageBase64)
                .createdAt(LocalDateTime.now())
                .likes(0)
                .shares(0)
                .build();

        return postRepository.save(post);
    }

    public PostEntity toggleLike(String postId, String userId) {
        Optional<PostEntity> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            PostEntity post = optionalPost.get();

            if (post.getLikedByUsers().contains(userId)) {
                // User already liked, so unlike
                post.getLikedByUsers().remove(userId);
                post.setLikes(post.getLikes() - 1);
            } else {
                // User hasn't liked, so like
                post.getLikedByUsers().add(userId);
                post.setLikes(post.getLikes() + 1);
            }

            return postRepository.save(post);
        }
        return null;
    }

    public PostEntity incrementShare(String postId) {
        Optional<PostEntity> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            PostEntity post = optionalPost.get();
            post.setShares(post.getShares() + 1);
            return postRepository.save(post);
        }
        return null;
    }

    public boolean isLikedByUser(String postId, String userId) {
        Optional<PostEntity> optionalPost = postRepository.findById(postId);
        return optionalPost.map(post -> post.getLikedByUsers().contains(userId)).orElse(false);
    }
}