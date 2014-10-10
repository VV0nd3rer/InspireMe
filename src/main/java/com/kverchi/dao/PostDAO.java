package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Post;

public interface PostDAO extends GenericDAO<Post>{
	
	public List<Post> getSightPosts(int _sightId, int _usrId);
	
	
}
