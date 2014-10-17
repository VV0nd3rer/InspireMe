package com.kverchi.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.kverchi.dao.RoleDAO;
import com.kverchi.dao.UserDAO;
import com.kverchi.domain.Role;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDAO userDAO;
	@Autowired 
	RoleDAO roleDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private  static final String noAvatarImgName = "noavatar.jpg";
	
	@Transactional(readOnly = false)
	public boolean registerAccount(User user, Errors errors) {
		boolean valid = !errors.hasErrors();
		if (valid) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleDAO.findByName("user"));
			user.setRoles(roles);
			String password = user.getPassword();
	    	   user.setPassword(passwordEncoder.encode(password));
	    	   user.setEnabled(true);
	    	   
			UserData newUserData = new UserData();
			newUserData.setAvatarUrl(noAvatarImgName);
			
			user.setUserData(newUserData);
			newUserData.setUser(user);
			userDAO.create(user);
		}
		return valid;
	}
	public boolean validateUsername(String usrName) {
		if (userDAO.findByUsername(usrName) != null) 
			return false;
		else
			return true;
	}
	public User getUserByUsername(String username) {
		User user =  userDAO.findByUsername(username);
		return user;
	}
	public List<User> getAllUsers() {
		List<User> users =  userDAO.getAllRecords();
		return users;
	}
	public User getUserById(int userId) {
		User user =  userDAO.getById(userId);
		return user;
	}
}
