package com.example.mongodemo.Controllers;

import com.example.mongodemo.Services.SolrService;
import com.example.mongodemo.repositories.api.PostRepository;
import com.example.mongodemo.resources.Post;
import com.example.mongodemo.resources.PostVo;
import com.example.mongodemo.utility.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class PostController2 {

    @Autowired
    private PostRepository postRepository;



    @Autowired
    SolrService solrService;

//    @PostMapping("/addPost")
//    public DeferredResult<ApiResponse<Post>> savePost(@RequestBody Post postBody)
//    {
//
//        System.out.println("Start mongo thread is::::    "+Thread.currentThread().getName());
//String strUUID = UUID.randomUUID().toString();
//        postBody.setPostId(strUUID);
//        ApiResponse<Post> result = new ApiResponse<Post>();
//        DeferredResult<ApiResponse<Post>> defRes = new DeferredResult<>();
//System.out.println("strUUID:::::   "+strUUID);
//  postRepository.insert(postBody)
//          .doOnError(error -> {
//              result.setStatus(HttpStatus.NOT_MODIFIED);
//              result.setMessage("Not added");
//              defRes.setErrorResult(error);
//          })
//           .doOnNext(body -> {
//               System.out.println("In------ Repo thread:::  "+ Thread.currentThread().getName());
//			   result.setStatus(HttpStatus.OK);
//			   result.setMessage("Post Added"+ body.getPostId());
//			   result.setResponseObject(body);
//			   defRes.setResult(result);
//			   System.out.println("Here:::::   "+ body.getClass());
//            Mono <PostVo> cF= solrService.insertInSolr(postBody);
//           })
//           .subscribe(result1 ->
//           {
//
//
//           });
//
//
//
//
//        System.out.println("End mongo thread is::::    "+Thread.currentThread().getName());
//
//
//return defRes;
//    }



















    
//    @GetMapping("/findallPosts")
//    public Flux<Post> getPosts(){
//
//
//        return  postRepository.findAll();
//    }
//
//    @GetMapping("/post/{id}")
//    public Mono<Post> getPostById(@PathVariable String id){
//        Mono<Post> post=postRepository.findById(id);
//        return post;
//    }
//
//    @GetMapping("/post/location/{loc}")
//    public Flux<Post> getPostByLocation(@PathVariable String loc)
//    {
//        return postRepository.getPostByLocation(loc);
//    }
//


}
