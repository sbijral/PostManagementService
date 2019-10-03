package com.yolo.PostManagementService.repositories.api;

import org.springframework.stereotype.Repository;

import com.yolo.PostManagementService.resources.Post;

import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface PostRepositoryCustom {

    public Flux<Post> getPostByLocation(String location);
    
//    public Flux<Post> getAllSeekingPosts(String userId);
//    
//    public Flux<Post> getAllContributingPosts(String userId);
}
