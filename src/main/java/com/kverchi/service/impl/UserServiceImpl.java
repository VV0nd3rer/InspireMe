package com.kverchi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.kverchi.dao.UserDAO;
import com.kverchi.domain.User;
import com.kverchi.service.UserService;

@Service
@Transactional(readOnly = true)
/*@ImportResource("classpath:beans-web.xml")*/
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDAO userDAO;
	
	@Transactional(readOnly = false)
	public boolean registerAccount(User user, Errors errors) {
		System.out.println("user name: " + user.getUsername());
		//validateUsername(user.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			/*Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(roleDao.findByName("user"));*/
			/*user.setRoles(roles);*/
			userDAO.addUser(user);
			userDAO.addRole(1, user.getUsername());
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
	public boolean validateUsername(String login) {
		if (userDAO.getUserByLogin(login)!= null) 
			return false;
		return true;
	}
	/*private void validateUsername(User user, Errors errors) {
		if(userDao.getUserByPassword(user) == null) 
			errors.rejectValue("login", "error.login", null, null);
	}*/
}
