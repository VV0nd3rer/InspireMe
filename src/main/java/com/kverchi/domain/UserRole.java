package com.kverchi.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@IdClass(UserRoleId.class)
@Table(name = "user_role")
public class UserRole implements Serializable {
	
	@Id private int user_id;
	@Id private int role_id;
	
	@Column(name="user_id")
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		this.user_id = userId;
	}

	@Column(name="role_id")
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	
}

