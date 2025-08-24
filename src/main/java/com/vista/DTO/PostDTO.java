package com.vista.DTO;

import java.time.Instant;

public class PostDTO {
    private String id;
    private String imageUrl;
    private String caption;
    private String username;
    private String userId;
    private long likes;
    private long shares;
    private Instant createdAt;
    private boolean likedByMe;
}
