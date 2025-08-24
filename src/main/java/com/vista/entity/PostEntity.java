package com.vista.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "posts")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class PostEntity {
 @Id
 private String id;
 private String username;
 private String imageBase64;
 private String caption;
 private LocalDateTime createdAt;
 private int likes;
 private int shares;
 private Set<String> likedByUsers;

 // Initialize empty set for likedByUsers if null
 public Set<String> getLikedByUsers() {
  if (likedByUsers == null) {
   likedByUsers = new HashSet<>();
  }
  return likedByUsers;
 }
}