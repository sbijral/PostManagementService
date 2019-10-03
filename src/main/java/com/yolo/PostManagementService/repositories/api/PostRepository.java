package com.yolo.PostManagementService.repositories.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.yolo.PostManagementService.resources.Post;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String>,PostRepositoryCustom{


}
