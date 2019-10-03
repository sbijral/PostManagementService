package com.example.mongodemo.repositories.api;

import com.example.mongodemo.resources.Post;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface PostRepositoryCustom {

    public Flux<Post> getPostByLocation(String location);
    
//    public Flux<Post> getAllSeekingPosts(String userId);
//    
//    public Flux<Post> getAllContributingPosts(String userId);
}
