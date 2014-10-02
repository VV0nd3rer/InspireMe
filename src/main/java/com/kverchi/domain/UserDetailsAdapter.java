package com.kverchi.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsAdapter implements UserDetails {
	/*
	 * http://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	public UserDetailsAdapter(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> users = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			users.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		return users;
	}
    public int getId() {
    	return user.getUserId();
    }
	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return user.isEnabled();
	}

}
