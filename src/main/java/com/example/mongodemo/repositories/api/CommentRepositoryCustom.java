package com.example.mongodemo.repositories.api;

import com.example.mongodemo.resources.Comment;
import com.example.mongodemo.resources.Post;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {

    public Flux<Comment> getCommentByPostId(String postId);
}
