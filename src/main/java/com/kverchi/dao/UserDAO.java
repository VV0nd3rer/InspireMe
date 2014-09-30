package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.User;

public interface UserDAO {
	public void addUser(User user);
	public void updateUser(User user);
	public User getUserById(int userId);
	//public User getUserByPassword(User user);
	public List<User> getAllUsers();
	public void deleteUser(User user);
	//public void addRole(int role_id, int userId);
	public User findByUsername(String username);
}
