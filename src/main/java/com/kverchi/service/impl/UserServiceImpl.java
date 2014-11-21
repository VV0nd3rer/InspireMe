package com.kverchi.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kverchi.dao.RoleDAO;
import com.kverchi.dao.UserDAO;
import com.kverchi.domain.Role;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.service.EmailService;
import com.kverchi.service.ResetPasswordService;
import com.kverchi.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired 
	private UserDAO userDAO;
	@Autowired 
	private RoleDAO roleDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private  static final String noAvatarImgName = "noavatar.jpg";
	@Autowired
	private EmailService emailService;
	@Autowired
	private ResetPasswordService resetPasswordService;
	private static final String CONFIRM_REGISTRATION = "registerConfirm.vm";
	private static final String RESET_PASSWORD = "resetPassword.vm";
	
	@Transactional(readOnly = false)
	public boolean registerAccount(User user) {
		if (confirmEmail(user)) {
			Set<Role> roles = new HashSet<Role>();
			roles.add(roleDAO.findByName("user"));
			user.setRoles(roles);
			String password = user.getPassword();

			user.setPassword(passwordEncoder.encode(password));
			user.setEnabled(false);

			UserData newUserData = new UserData();
			newUserData.setAvatarUrl(noAvatarImgName);

			user.setUserData(newUserData);
			newUserData.setUser(user);
			userDAO.create(user);
			return true;
		}
		else
			return false;
	}
	@Override
	public void resetPassword(User user) {
		User remissUser = new User();
		remissUser = userDAO.findByEmail(user.getEmail());
		remissUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.update(remissUser);
	}
	private boolean confirmEmail(User user) {
		return emailService.sendEmail(user, CONFIRM_REGISTRATION, 
								      "Registration", "admin@mail.com", "admin");
	}
	public boolean sendResetLink(String email) {
		User user = null;
		/*String token = new String();*/
		user = userDAO.findByEmail(email);
		if(user == null || !user.isEnabled()) 
		  return false;
		//Generate random token
		String token = resetPasswordService.generateToken();
		//Write token to DB
		resetPasswordService.setToken(user.getUserId(), token);
		return emailService.sendEmail(user, token, RESET_PASSWORD, 
									  "Recovering password", "admin@mail.com", "admin");
	}
	public boolean isValidUsername(String usrName) {
		if (userDAO.findByUsername(usrName) != null) 
			return false;
		else
			return true;
	}
	public boolean isValidEmail(String email) {
		if(userDAO.findByEmail(email) != null)
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
	public boolean setEnabled(User user) {
		if(user != null && !user.isEnabled()) {
			user.setEnabled(true);
			userDAO.update(user);
			return true;
		}
		else 
			return false;
	}
}
