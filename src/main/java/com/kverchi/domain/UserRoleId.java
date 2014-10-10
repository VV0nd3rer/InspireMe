package com.kverchi.domain;

import java.io.Serializable;

public class UserRoleId implements Serializable{
	
		private static final long serialVersionUID = -4554212435274484595L;
		private int userId;
		private int role_id;
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getRole_id() {
			return role_id;
		}
		public void setRole_id(int role_id) {
			this.role_id = role_id;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	}
