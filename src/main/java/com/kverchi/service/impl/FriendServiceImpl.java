package com.kverchi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.FriendDAO;
import com.kverchi.dao.UserDAO;
import com.kverchi.domain.Friend;
import com.kverchi.domain.User;
import com.kverchi.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
	@Autowired 
	private FriendDAO friendDAO;
	@Autowired 
	private UserDAO userDAO;
	@Override
	public void addFriend(int userId, int friendId) {
		Friend friend = new Friend();
		friend.setFriendOneId(userId);
		friend.setFriendTwoId(friendId);
		friendDAO.addFriend(friend);
	}
	
	public List<User> getUserFriends(int userId, int status){
		List<Integer> userFriendsID = friendDAO.getFriendsId(userId, status);
		List<User> userFriends = new ArrayList<User>();
		for(int id: userFriendsID){
			userFriends.add(userDAO.getUserById(id));
		}
		return userFriends;
	}
	
	public void removeFriend(int userId, int friendId, String referer) {
		Friend friend = new Friend();
		boolean check = friendDAO.isExisted(userId, friendId);
		if(check){
			friend.setFriendOneId(userId);
			friend.setFriendTwoId(friendId);
			
		}else{
			friend.setFriendOneId(friendId);
			friend.setFriendTwoId(userId);
		}
		if(referer.equals("friends")){
			friend.setStatus(1);
		}else if (referer.equals("requests")){
			friend.setStatus(0);
		}
		friendDAO.removeFriend(friend);
	}
	
	public void acceptFriend(int userId, int friendId) {
		Friend friend = new Friend();
		boolean check = friendDAO.isExisted(userId, friendId);
		if(check){
			friend.setFriendOneId(userId);
			friend.setFriendTwoId(friendId);
			
		}else{
			friend.setFriendOneId(friendId);
			friend.setFriendTwoId(userId);
		}
		friend.setStatus(1);
	
		friendDAO.acceptFriend(friend);
	}

}
