package com.kverchi.domain;

import java.io.Serializable;

public class UserRoleId implements Serializable{
	
		private static final long serialVersionUID = -4554212435274484595L;
		private int user_id;
		private int role_id;
		
		public int getUser_id() {
			return user_id;
		}
		public void setUser_id(int userId) {
			this.user_id = userId;
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
