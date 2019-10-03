package com.yolo.PostManagementService.Services;

import org.springframework.stereotype.Service;

import com.yolo.PostManagementService.resources.Post;
import com.yolo.PostManagementService.resources.PostVo;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class SolrService {

   public Mono<PostVo> insertInSolr(Post post) {
    ///   Thread.sleep(2000);
      // System.out.println("Solr thread is::::    "+Thread.currentThread().getName());
//       String loc= post.getLocation();
//       post.setLocation(loc+"solr");
//       PostVo pVo = new PostVo(post);
 return  null;
   }

public void updateinSolr(String postId, String userId) {
	// TODO Auto-generated method stub
	
}
}
