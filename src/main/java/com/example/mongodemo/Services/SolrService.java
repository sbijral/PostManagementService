package com.example.mongodemo.Services;

import com.example.mongodemo.resources.Post;
import com.example.mongodemo.resources.PostVo;
import org.springframework.stereotype.Service;
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
