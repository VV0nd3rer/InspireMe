package com.kverchi.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@NamedQuery(
		name = "findUserByUsername",
		query = "from User where username = :username")
@Entity
@Table(name="users")
public class User {
	private int userId;
	private String username;
    private String password;
    private boolean enabled;
    private Collection<Role> roles = new HashSet<Role>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public boolean isEnabled() { return enabled; }
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "user_role",
		joinColumns = { @JoinColumn(name = "userId", referencedColumnName = "userId") },
		inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	public Collection<Role> getRoles() { return roles; }
	
	public void setRoles(Collection<Role> roles) { this.roles = roles; }
	
	public void printUser() {
		System.out.println(username + " : " + password);
	}
}
