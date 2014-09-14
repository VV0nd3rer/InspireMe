package com.kverchi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.PostDAO;
import com.kverchi.domain.Post;

@Service
public class PostServiceImpl implements PostService {
	@Autowired 
	private PostDAO postDAO;
	
	public List<Post> showAllPosts() {
		List<Post> posts = null;
		posts = postDAO.getAllPosts();
		return posts;
	}

}
