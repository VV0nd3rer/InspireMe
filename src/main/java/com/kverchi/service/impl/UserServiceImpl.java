package com.kverchi.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.kverchi.dao.RoleDAO;
import com.kverchi.dao.UserDAO;
import com.kverchi.domain.Role;
import com.kverchi.domain.User;
import com.kverchi.service.UserService;

@Service
@Transactional(readOnly = true)
/*@ImportResource("classpath:beans-web.xml")*/
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDAO userDAO;
	@Autowired 
	RoleDAO roleDAO;
	
	@Transactional(readOnly = false)
	public boolean registerAccount(User user, Errors errors) {
		//validateUsername(user.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleDAO.findByName("user"));
			user.setRoles(roles);
			userDAO.addUser(user);
			//userDAO.addRole(1, user.getUserId());
		}
		return valid;
	}
	//@Transactional(readOnly = true)
	/*public boolean loginAccount(User user, Errors errors) {
		validateUsername(user, errors);
		return !errors.hasErrors();
	}*/
	/*private void validateUsername(String login, Errors errors) {
		if (userDao.getUserByLogin(login)!= null) {
			System.out.println("In not null validateUsrname");
			errors.rejectValue("login", "error.duplicate",
			new String[] { login }, null);
		}
	}*/
	public boolean validateUsername(String usrName) {
		if (userDAO.findByUsername(usrName)!= null) 
			return false;
		return true;
	}
	/*private void validateUsername(User user, Errors errors) {
		if(userDao.getUserByPassword(user) == null) 
			errors.rejectValue("login", "error.login", null, null);
	}*/
	public User getUserByUsername(String username) {
		User user =  userDAO.findByUsername(username);
		//if (user != null) { Hibernate.initialize(user.getRoles()); }
		return user;
	}
}
