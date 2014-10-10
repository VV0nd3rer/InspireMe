package com.kverchi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.UserDataDAO;
import com.kverchi.domain.UserData;
import com.kverchi.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired private UserDataDAO userDataDAO;
	
	@Override
	public UserData getUserData(int userId) {
		UserData ud = userDataDAO.getById(userId);
		return ud;
	}

	@Override
	public void createUserData(UserData data) {
		userDataDAO.create(data);

	}

	@Override
	public void deleteUserData(int userId) {
		userDataDAO.delete(userDataDAO.getById(userId));

	}

	@Override
	public void updateUserData(UserData data) {
		userDataDAO.update(data);

	}

}
