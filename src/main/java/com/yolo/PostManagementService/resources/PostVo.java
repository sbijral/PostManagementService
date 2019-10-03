package com.yolo.PostManagementService.resources;

public class PostVo {

    private Post post;
    private boolean alreadyLiked;
    private boolean alreadFollowing;

    public PostVo(Post post) {
        this.post=post;
    }

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public boolean isAlreadyLiked() {
		return alreadyLiked;
	}

	public void setAlreadyLiked(boolean alreadyLiked) {
		this.alreadyLiked = alreadyLiked;
	}

	public boolean isAlreadFollowing() {
		return alreadFollowing;
	}

	public void setAlreadFollowing(boolean alreadFollowing) {
		this.alreadFollowing = alreadFollowing;
	}
    

}
