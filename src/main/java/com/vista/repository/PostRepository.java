package com.vista.repository;

import com.vista.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<PostEntity, String> {
    List<PostEntity> findAllByOrderByCreatedAtDesc();
}