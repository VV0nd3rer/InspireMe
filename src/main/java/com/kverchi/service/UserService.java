package com.kverchi.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.kverchi.domain.User;

@Service
public interface UserService {
	boolean registerAccount(User user, Errors errors);
	public boolean validateUsername(String usrName);
	User getUserByUsername(String username);
}
