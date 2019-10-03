package com.example.mongodemo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongodemo.repositories.api.PostRepository;
import com.example.mongodemo.resources.Post;

import reactor.core.publisher.Mono;

@Service
public class MongoPostService {
	
	@Autowired 
	PostRepository postRepo;

	
	public void incrementCommentCount(String postId) {
		// TODO Auto-generated method stub
		
	postRepo.findById(postId)
			.doOnNext(post->
			{
				if(null!=post.getCommentsCounts()) 
					post.setCommentsCounts(post.getCommentsCounts()+1);
				else
					post.setCommentsCounts(1);
				
				postRepo.save(post).retry().subscribe();
			}
					
					
					)
			.subscribe();
	
	
		
	}
	
	public Mono<Post> incrementLikeCount(String postId, String userId) {
		// TODO Auto-generated method stub
		postRepo.findById(postId)
	.doOnNext(post->
	
			{
				if(null!=post.getLikeCounts()) {
					if(!post.getLikerIds().contains(userId)) {
					post.setLikeCounts(post.getLikeCounts()+1);
				List<String> likers=	post.getLikerIds();
				likers.add(userId);
				post.setLikerIds(likers);
					}
				}
				else {
					post.setLikeCounts(1);
					List<String> likers= new ArrayList<String>();
					likers.add(userId);
					post.setLikerIds(likers);
				}
				postRepo.save(post).retry().subscribe();
				
				
				
			})
	.subscribe();
		
		Mono<Post> updatedPost= postRepo.findById(postId);
	
		return updatedPost;
	}
	
	public Mono<Post> decrementLikeCount(String postId, String userId) {
		// TODO Auto-generated method stub
		
	postRepo.findById(postId)
	.doOnNext(post->
	{
		
		Integer count=post.getLikeCounts();
		if(count>=0) {
		post.setLikeCounts(--count);
		}
		List <String> likers=post.getLikerIds();
		likers.remove(userId);
		post.setLikerIds(likers);
		postRepo.save(post).retry().subscribe();
		
		
	})
	.subscribe();
	
	Mono<Post> updatedPost= postRepo.findById(postId);
		return updatedPost;
	}

	public Mono<Post> addFollower(String postId, String userId) {
		// TODO Auto-generated method stub
		
	Post post=	(Post) postRepo.findById(postId).subscribe();
	if(null!=post.getPostFollowers()) {
		if(!post.getPostFollowers().contains(userId)) {
	List<String> followers=	post.getPostFollowers();
	followers.add(userId);
	post.setPostFollowers(followers);
		}
	}
	else {
		
		List<String> followers= new ArrayList<String>();
		followers.add(userId);
		post.setPostFollowers(followers);
	}
	Mono <Post> postMono=postRepo.save(post).retry();
		return postMono;
	}
	
	public Mono<Post> decrementFollower(String postId, String userId) {
		// TODO Auto-generated method stub
		
	Post post=	(Post) postRepo.findById(postId).subscribe();
	List <String> followers=post.getPostFollowers();
	followers.remove(userId);
	post.setLikerIds(followers);
	Mono <Post> postMono=postRepo.save(post).retry();
		return postMono;
	}
	
	

}
