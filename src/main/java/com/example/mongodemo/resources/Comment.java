package com.example.mongodemo.resources;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
public class Comment {

	@Id
	private String commentId;
	private String description;
	private String commenterId;
	private String commenterName;
	private String postId;
	public String getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(String commenterId) {
		this.commenterId = commenterId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserID() {
		return commenterId;
	}
	public void setUserID(String commenterId) {
		this.commenterId = commenterId;
	}
	public String getCommenterName() {
		return commenterName;
	}
	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}
	
	
	
}
