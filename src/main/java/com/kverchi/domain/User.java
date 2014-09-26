package com.kverchi.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	private int userId;
	private String username;
    private String password;
    private int enabled;
    private Set<UserRole> roles = new HashSet<UserRole>();

	@Id
	@Column(name="userId")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Column(name="username")
    public String getUsername() {
	   return username;
	}
	public void setUsername(String username) {
	   this.username = username;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="enabled")
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	/*public Set<Role> getRoles() { return roles; }
	
	public void setRoles(Set<UserRole> roles) { this.roles = roles; }*/
	
	public void printUser() {
		System.out.println(username + " : " + password);
	}
}
