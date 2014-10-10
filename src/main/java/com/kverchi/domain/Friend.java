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
	@Id private int friendOneId;
	@Id private int friendTwoId;
		private int status;
	
	@Column(name="friendOneId")
	public int getFriendOneId() {
		return friendOneId;
	}
	public void setFriendOneId(int friendOneId) {
		this.friendOneId = friendOneId;
	}
	
	@Column(name="friendTwoId")
	public int getFriendTwoId() {
		return friendTwoId;
	}
	public void setFriendTwoId(int friendTwoId) {
		this.friendTwoId = friendTwoId;
	}
	
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}

