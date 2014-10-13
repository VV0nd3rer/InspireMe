package com.kverchi.domain;

import java.io.Serializable;

import javax.persistence.Id;

public class FriendId implements Serializable{
	
	private static final long serialVersionUID = 1530088925331701600L;
	private int friend_one_id;
	private int friend_two_id;
	 
	 public FriendId(){
	
	 }
	 public FriendId(int userId, int friendId){
		 this.friend_one_id = userId;
		 this.friend_two_id = friendId;
	 }
	 
	 
	public int getFriend_one_id() {
		return friend_one_id;
	}
	public void setFriend_one_id(int friend_one_id) {
		this.friend_one_id = friend_one_id;
	}
	public int getFriend_two_id() {
		return friend_two_id;
	}
	public void setFriend_two_id(int friend_two_id) {
		this.friend_two_id = friend_two_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
}
