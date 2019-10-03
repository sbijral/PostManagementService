package com.yolo.PostManagementService.resources;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yolo.PostManagementService.enums.PostStatus;
import com.yolo.PostManagementService.enums.PostType;

@Document(collection = "Post")
public class Post {

	@Id
	private String postId;
	private String location;
	private String title;
	private String description;
	private String postedBy;
	private String category;
	private boolean isPromoted;
	private PostType type;
	private Long createdTime;
	private Long lastUpdatedTime;
	private List<String> spamReporters;
	private List<String> photoLinks;
	private Integer likeCounts;
	private Integer commentsCounts;
	private List<String> likerIds;
	private String posterName;
	private String posterProfilePictureLink;
	private List<String> postFollowers;

	private PostStatus status;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isPromoted() {
		return isPromoted;
	}

	public void setPromoted(boolean isPromoted) {
		this.isPromoted = isPromoted;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public PostType getType() {
		return type;
	}

	public void setType(PostType type) {
		this.type = type;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public List<String> getSpamReporters() {
		return spamReporters;
	}

	public void setSpamReporters(List<String> spamReporters) {
		this.spamReporters = spamReporters;
	}

	public List<String> getPhotoLinks() {
		return photoLinks;
	}

	public void setPhotoLinks(List<String> photoLinks) {
		this.photoLinks = photoLinks;
	}

	public Integer getLikeCounts() {
		return likeCounts;
	}

	public void setLikeCounts(Integer likeCounts) {
		this.likeCounts = likeCounts;
	}

	public Integer getCommentsCounts() {
		return commentsCounts;
	}

	public void setCommentsCounts(Integer commentsCounts) {
		this.commentsCounts = commentsCounts;
	}

	public List<String> getLikerIds() {
		return likerIds;
	}

	public void setLikerIds(List<String> likerIds) {
		this.likerIds = likerIds;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getPosterProfilePictureLink() {
		return posterProfilePictureLink;
	}

	public void setPosterProfilePictureLink(String posterProfilePictureLink) {
		this.posterProfilePictureLink = posterProfilePictureLink;
	}

	public List<String> getPostFollowers() {
		return postFollowers;
	}

	public void setPostFollowers(List<String> postFollowers) {
		this.postFollowers = postFollowers;
	}

	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	
}
