package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Post;

public interface PostDAO {
	public Post getPost(int _postId);
	public List<Post> getSightPosts(int _sightId, int _usrName);
	public List<Post> getAllPosts();
	public void createPost(Post post);
	public void deletePost(int _postId);
	public void updatePost(Post post);
}
