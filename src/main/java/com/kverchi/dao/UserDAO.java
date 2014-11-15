package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.User;

public interface UserDAO extends GenericDAO<User>{
	
	public User findByUsername(String username);
	public User findByEmail(String email);
}
