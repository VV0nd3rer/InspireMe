package com.kverchi.domain;

import java.io.Serializable;

public class FriendId implements Serializable{
	
	private static final long serialVersionUID = 1530088925331701600L;
	private int friendOneId;
	 private int friendTwoId;
	 
	 public FriendId(){
	
	 }
	 public FriendId(int userId, int friendId){
		 setFriendOneId(userId);
		 setFriendTwoId(friendId);
	 }
	 
	public int getFriendOneId() {
		return friendOneId;
	}
	public void setFriendOneId(int friendOneId) {
		this.friendOneId = friendOneId;
	}
	public int getFriendTwoId() {
		return friendTwoId;
	}
	public void setFriendTwoId(int friendTwoId) {
		this.friendTwoId = friendTwoId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
}
