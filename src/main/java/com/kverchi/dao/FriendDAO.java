package com.kverchi.dao;

import java.util.List;
import com.kverchi.domain.Friend;
import com.kverchi.domain.User;



public interface FriendDAO {
	public void addFriend(Friend friend);
	public List<Integer> getFriendsId(int userId, int status);
	public void removeFriend(Friend friend);
	public void acceptFriend(Friend friend);
	public boolean isExisted(int userId, int friendId);
	
}
