package com.kverchi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.kverchi.domain.User;

@Service
public interface UserService {
	boolean registerAccount(User user, Errors errors);
	public boolean validateUsername(String usrName);
	public User getUserByUsername(String username);
	public User getUserById(int userId);
	public List<User> getAllUsers();
	public boolean setEnabled(User user);
}
