package com.kverchi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.PostDAO;
import com.kverchi.domain.Post;
import com.kverchi.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired 
	private PostDAO postDAO;

	public Post showPost(int _postId) {
		return postDAO.getPost(_postId);
	}

	public List<Post> showSightPosts(int _sightId, int _usrName) {
		return postDAO.getSightPosts(_sightId, _usrName);
	}

	public List<Post> showAllPosts() {
		return postDAO.getAllPosts();
	}

	public void createPost(Post post) {
		postDAO.createPost(post);
	}

	public void deletePost(int _postId) {
		postDAO.deletePost(_postId);
	}

	public void updatePost(Post post) {
		postDAO.updatePost(post);
	}
}
