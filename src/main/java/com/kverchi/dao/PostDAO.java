package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Post;

public interface PostDAO {
	public Post getPost(int _postId);
	public List<Post> getSightPosts(int _sightId);
	public List<Post> getAllPosts();
	public void addPost(Post post);
}
