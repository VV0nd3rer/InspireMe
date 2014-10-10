package com.kverchi.service;


import com.kverchi.domain.UserData;

public interface UserDataService {
	public UserData getUserData(int userId);
	public void createUserData(UserData data);
	public void deleteUserData(int userId);
	public void updateUserData(UserData data);
}
