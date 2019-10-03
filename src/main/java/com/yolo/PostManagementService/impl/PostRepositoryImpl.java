package com.yolo.PostManagementService.impl;


import com.yolo.PostManagementService.enums.PostType;
import com.yolo.PostManagementService.repositories.api.PostRepositoryCustom;
import com.yolo.PostManagementService.resources.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import java.util.List;

public class PostRepositoryImpl implements PostRepositoryCustom {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;


    @Override
    public Flux<Post> getPostByLocation(String location) {
        Query query = new Query();

        query.addCriteria(Criteria
                .where("location").is(location));
        query.limit(2);
        Flux<Post> posts = mongoTemplate.find(query,Post.class);
        return posts;
    }

//
//	@Override
//	public Flux<Post> getAllSeekingPosts(String userId) {
//        Query query = new Query();
//
//        query.addCriteria(
//                Criteria.where("postedBy").exists(true).andOperator(
//		         Criteria.where("postedBy").is(userId),
//                      Criteria.where("type").is(PostType.SEEKER)
//	            )
//             );
//        Flux<Post> posts = mongoTemplate.find(query,Post.class);
//        return posts;
//    }
//
//
//	@Override
//	public Flux<Post> getAllContributingPosts(String userId) {
//        Query query = new Query();
//
//        query.addCriteria(
//                Criteria.where("postedBy").exists(true).andOperator(
//		         Criteria.where("postedBy").is(userId),
//                      Criteria.where("type").is(PostType.CONTRIBUTOR)
//	            )
//             );
//        Flux<Post> posts = mongoTemplate.find(query,Post.class);
//        return posts;
//    }
}
