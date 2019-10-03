package com.example.mongodemo.repositories.api;

import com.example.mongodemo.resources.Comment;
import com.example.mongodemo.resources.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String>,CommentRepositoryCustom{


}
