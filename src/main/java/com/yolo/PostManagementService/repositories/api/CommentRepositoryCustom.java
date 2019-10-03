package com.yolo.PostManagementService.repositories.api;

import org.springframework.stereotype.Repository;

import com.yolo.PostManagementService.resources.Comment;
import com.yolo.PostManagementService.resources.Post;

import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface CommentRepositoryCustom {

    public Flux<Comment> getCommentByPostId(String postId);
}
