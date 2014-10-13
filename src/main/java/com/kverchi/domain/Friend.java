package com.kverchi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(FriendId.class)
@Table(name = "users_friends")
public class Friend {
	@Id private int friend_one_id;
	@Id private int friend_two_id;
	
	
	@Column(name="friend_one_id")
		public int getFriend_one_id() {
		return friend_one_id;
	}
	public void setFriend_one_id(int friend_one_id) {
		this.friend_one_id = friend_one_id;
	}
	@Column(name="friend_two_id")
	public int getFriend_two_id() {
		return friend_two_id;
	}
	public void setFriend_two_id(int friend_two_id) {
		this.friend_two_id = friend_two_id;
	}
		private int status;
	
		@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}

