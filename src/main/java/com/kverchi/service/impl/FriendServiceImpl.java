package com.kverchi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.FriendDAO;
import com.kverchi.dao.GenericDAO;
import com.kverchi.dao.UserDAO;
import com.kverchi.domain.Friend;
import com.kverchi.domain.FriendId;
import com.kverchi.domain.User;
import com.kverchi.domain.UserData;
import com.kverchi.service.FriendService;
import com.kverchi.tools.Pair;

@Service
public class FriendServiceImpl implements FriendService {
	@Autowired 
	private FriendDAO friendDAO;
	@Autowired 
	private UserDAO userDAO;
		
	@Override
	public void addFriend(int userId, int friendId) {
		Friend friend = new Friend();
		friend.setFriend_one_id(userId);
		friend.setFriend_two_id(friendId);
		friendDAO.create(friend);
	}
	
	public List<Pair<User, Integer>> getPeopleList(int userId){
		List<Pair<User, Integer>> peopleList = new ArrayList<Pair<User, Integer>>();
		
		List<Integer> temp = friendDAO.getFriendsId(userId, 0);
		List<Integer> tempResult = friendDAO.getFriendsId(userId, 1);
		tempResult.addAll(temp);
		List<User> pListTemp = friendDAO.getPeople(userId);
		for(User user : pListTemp){
			if(tempResult.contains(user.getUserId()))
			{
				peopleList.add(new Pair<User, Integer>(user, 1));
			}
			else
			{
				peopleList.add(new Pair<User, Integer>(user, 0));
			}
		}
			
		return peopleList;
	}
	
public List<Pair<User, Integer>> getPeopleList(int userId, String fragment){
		
		List<Pair<User, Integer>> peopleList = new ArrayList<Pair<User, Integer>>();
		
		List<Integer> temp = friendDAO.getFriendsId(userId, 0);
		List<Integer> tempResult = friendDAO.getFriendsId(userId, 1);
		tempResult.addAll(temp);
		List<User> pListTemp = friendDAO.getPeople(userId, fragment);
		for(User user : pListTemp){
			if(tempResult.contains(user.getUserId()))
			{
				peopleList.add(new Pair<User, Integer>(user, 1));
			}
			else
			{
				peopleList.add(new Pair<User, Integer>(user, 0));
			}
		}
			
		return peopleList;
	}
	
	public List<User> getUserFriends(int userId, int status){
		List<User> userFriends = friendDAO.getFriends(userId, status);
		return userFriends;
	}
	
	public void removeFriend(int userId, int friendId, String referer) {
		Friend friend = new Friend();
		Friend friendCheck = friendDAO.getById(new FriendId(userId, friendId));
		
		if(friendCheck!=null){
			friend.setFriend_one_id(userId);
			friend.setFriend_two_id(friendId);
			
		}else{
			friend.setFriend_one_id(friendId);
			friend.setFriend_two_id(userId);
		}
		if(referer.equals("friends")){
			friend.setStatus(1);
		}else if (referer.equals("requests")){
			friend.setStatus(0);
		}
		friendDAO.delete(friend);
		
	}
	
	public void acceptFriend(int userId, int friendId) {
		Friend friend = new Friend();
		Friend friendCheck = friendDAO.getById(new FriendId(userId, friendId));
		
		if(friendCheck!=null){
			friend.setFriend_one_id(userId);
			friend.setFriend_two_id(friendId);
			
		}else{
			friend.setFriend_one_id(friendId);
			friend.setFriend_two_id(userId);
		}
		friend.setStatus(1);
		friendDAO.update(friend);
	}
	
	public List<User> sortRequests(List<User> requestList, int userId, int sortParam) {
		List<User> sortedList = new ArrayList<User>();
				
		for(User user : requestList){
			Friend friendCheck = friendDAO.getById(new FriendId(userId, user.getUserId()));
			
			if(friendCheck!=null && sortParam==0){
				sortedList.add(user);
			}
			else if(friendCheck==null && sortParam==1){
				sortedList.add(user);
			}
		}
		return sortedList;
	}

}
