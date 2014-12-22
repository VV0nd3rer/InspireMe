package com.kverchi.dao;

import java.util.List;
import com.kverchi.domain.Friend;
import com.kverchi.domain.User;



public interface FriendDAO extends GenericDAO<Friend>{
	
	public List<Integer> getFriendsId(int userId, int status);
	public List<User> getFriends(int userId, int status);
	public List<User> getPeople(int userId, String fragment);
	
}
