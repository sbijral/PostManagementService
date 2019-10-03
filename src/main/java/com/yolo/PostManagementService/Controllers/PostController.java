package com.yolo.PostManagementService.Controllers;

import com.yolo.PostManagementService.Services.MongoPostService;
import com.yolo.PostManagementService.Services.SolrService;
import com.yolo.PostManagementService.enums.PostStatus;
import com.yolo.PostManagementService.repositories.api.CommentRepository;
import com.yolo.PostManagementService.repositories.api.PostRepository;
import com.yolo.PostManagementService.resources.Comment;
import com.yolo.PostManagementService.resources.Post;
import com.yolo.PostManagementService.resources.PostVo;
import com.yolo.PostManagementService.utility.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoPostService mongoPostService;
    @Autowired
    SolrService solrService;

    @PostMapping("/addPost")
    public DeferredResult<ApiResponse<Post>> savePost(@RequestBody Post postBody)
	{
		postBody.setPostId(UUID.randomUUID().toString());
		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		// Check the Moderation flag and decide
		postRepository.insert(postBody).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to Add the user " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).doOnNext(body -> {
				result.setStatus(HttpStatus.OK);
				result.setMessage("Post Added" + body.getPostId());
				result.setResponseObject(body);
				defResult.setResult(result);
				solrService.insertInSolr(postBody);

		}).subscribe();

		return defResult;
	}
    
    @GetMapping("/post/{postId}")
    public DeferredResult<ApiResponse<Post>> getPost(@PathVariable String postId)
	{

		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		postRepository.findById(postId).doOnNext(post -> {
			result.setStatus(HttpStatus.OK);
			result.setResponseObject(post);
			defResult.setResult(result);
		}

		).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to Add the user " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;
	}
    
    @DeleteMapping("/post/{postId}")
    public DeferredResult<ApiResponse<Post>> deletePost(@PathVariable String postId)
    {
    ApiResponse<Post> result = new ApiResponse<Post>();
	DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
	postRepository.deleteById(postId).doOnNext(post -> {
		result.setStatus(HttpStatus.OK);
		defResult.setResult(result);
	}

	).doOnError(error -> {
		result.setStatus(HttpStatus.NOT_MODIFIED);
		result.setMessage("Not ABle to Delete the user " + error);
		result.setDebugMessage(error.getCause().toString());
		defResult.setResult(result);
	}).subscribe();

	return defResult;
}
    
    @PostMapping("/post/{postId}/addComment")
    public DeferredResult<ApiResponse<Comment>> addComment(@PathVariable String postId, @RequestBody Comment commentBody)

	{
		commentBody.setCommentId(UUID.randomUUID().toString());
		ApiResponse<Comment> result = new ApiResponse<Comment>();
		DeferredResult<ApiResponse<Comment>> defResult = new DeferredResult<>();
		commentRepository.insert(commentBody).doOnNext(comment -> {
			result.setResponseObject(comment);
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
			mongoPostService.incrementCommentCount(postId);
			solrService.updateinSolr(postId, commentBody.getUserID());
		}).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to Add the Comment " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    
    @PutMapping("/post/{postId}/closePost")
    public DeferredResult<ApiResponse<Post>> closePost(@PathVariable String postId)
	{

		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		Mono<Post> postMono = postRepository.findById(postId);
		Post post = (Post) postMono.subscribe();
		post.setStatus(PostStatus.CLOSED);
		postRepository.save(post).doOnNext(postResponse -> {
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
		}

		).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not able to close the Post " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    
//    @PostMapping("/post/{postId}/addComment")
//    public DeferredResult<ApiResponse<Comment>> addComment(@PathVariable String postId, @RequestBody Comment commentBody)
//
//	{
//		commentBody.setCommentId(UUID.randomUUID().toString());
//		ApiResponse<Comment> result = new ApiResponse<Comment>();
//		DeferredResult<ApiResponse<Comment>> defResult = new DeferredResult<>();
//		commentRepository.insert(commentBody).doOnNext(comment -> {
//			result.setResponseObject(comment);
//			result.setStatus(HttpStatus.OK);
//			defResult.setResult(result);
//			mongoPostService.incrementCommentCount(postId);
//			solrService.updateinSolr(postId);
//		}).doOnError(error -> {
//			result.setStatus(HttpStatus.NOT_MODIFIED);
//			result.setMessage("Not ABle to Add the Comment " + error);
//			result.setDebugMessage(error.getCause().toString());
//			defResult.setResult(result);
//		}).subscribe();
//
//		return defResult;
//
//	}
    
    @PostMapping("/post/{postId}/like/{userId}")
    public DeferredResult<ApiResponse<Post>> addLike(@PathVariable String postId, @PathVariable String userId)

	{
		
		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		
		mongoPostService.incrementLikeCount(postId,userId)
		.doOnNext(post -> {
			result.setResponseObject(post);
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
			solrService.updateinSolr(postId, userId);
		}).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to like the post " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    
    @PostMapping("/post/{postId}/unlike/{userId}")
    public DeferredResult<ApiResponse<Post>> decreaseLike(@PathVariable String postId,@PathVariable String userId)

	{
		
		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		
		mongoPostService.decrementLikeCount(postId,userId)
		.doOnNext(post -> {
			result.setResponseObject(post);
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
			solrService.updateinSolr(postId, userId);
		}).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to unlike the post " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    
    @PutMapping("/post/{postId}/follow/{userId}")
    public DeferredResult<ApiResponse<Post>> addFollower(@PathVariable String postId, @PathVariable String userId)

	{
		
		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		
		mongoPostService.addFollower(postId,userId)
		.doOnNext(post -> {
			result.setResponseObject(post);
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
			solrService.updateinSolr(postId, userId);
		}).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to follow the post " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    
    @PutMapping("/post/{postId}/unfollow/{userId}")
    public DeferredResult<ApiResponse<Post>> removeFollower(@PathVariable String postId, @PathVariable String userId)

	{
		
		ApiResponse<Post> result = new ApiResponse<Post>();
		DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
		
		mongoPostService.decrementFollower(postId, userId)
		.doOnNext(post -> {
			result.setResponseObject(post);
			result.setStatus(HttpStatus.OK);
			defResult.setResult(result);
			solrService.updateinSolr(postId, userId);
		}).doOnError(error -> {
			result.setStatus(HttpStatus.NOT_MODIFIED);
			result.setMessage("Not ABle to unfollow the post " + error);
			result.setDebugMessage(error.getCause().toString());
			defResult.setResult(result);
		}).subscribe();

		return defResult;

	}
    












//    @GetMapping("/post/findallSeekingPosts/{userId}")
//    public Flux<Post> getSeekingPosts(@PathVariable String userId)
//    
//    {ApiResponse<Post> result = new ApiResponse<Post>();
//	DeferredResult<ApiResponse<Post>> defResult = new DeferredResult<>();
//	postRepository.getAllSeekingPosts(userId).flatMap(post -> {
//		result.setStatus(HttpStatus.OK);
//		result.setResponseObject(post);
//		defResult.setResult(result);
//	}
//
//	).doOnError(error -> {
//		result.setStatus(HttpStatus.NOT_MODIFIED);
//		result.setMessage("Not ABle to Add the user " + error);
//		result.setDebugMessage(error.getCause().toString());
//		defResult.setResult(result);
//	}).subscribe();
//
//	return defResult;}

    

    @GetMapping("/post/location/{loc}")
    public Flux<Post> getPostByLocation(@PathVariable String loc)
    {
        return postRepository.getPostByLocation(loc);
    }
    
    @DeleteMapping("/deleteAll")
    public void deleteAll()
    {
        postRepository.deleteAll()
        .doOnError(error -> System.out.println("failed   "+ error))
        .doOnNext(onNext->System.out.println("succedd "+ onNext))
        .subscribe(System.out :: println);
    }




}
