package com.example.mongodemo.repositories.impl;


import com.example.mongodemo.repositories.api.CommentRepositoryCustom;
import com.example.mongodemo.repositories.api.PostRepositoryCustom;
import com.example.mongodemo.resources.Comment;
import com.example.mongodemo.resources.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import java.util.List;

public class CommentRepositoryImpl implements CommentRepositoryCustom {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;


    @Override
    public Flux<Comment> getCommentByPostId(String postId) {
        Query query = new Query();

        query.addCriteria(Criteria
                .where("postId").is(postId));
        
        Flux<Comment> comments = mongoTemplate.find(query,Comment.class);
        return comments;
    }
}
