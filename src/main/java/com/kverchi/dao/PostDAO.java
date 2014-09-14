package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Post;

public interface PostDAO {
	public Post getPost(int _id);
	public List<Post> getAllPosts();
}
