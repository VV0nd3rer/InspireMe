package com.kverchi.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	private String username;
    private String password;
    private int enabled;
    private Set<UserRole> roles = new HashSet<UserRole>();

	@Id
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
