package com.kverchi.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.kverchi.domain.Post;

@Service
public interface PostService {	
	public Post showPost(int _postId);
	public List<Post> showSightPosts(int _sightId, int _usrId);
	public List<Post> showAllPosts();
	public void createPost(Post post);
	public void deletePost(int _postId);
	public void updatePost(Post post);
}
