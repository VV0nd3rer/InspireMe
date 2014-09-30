package com.kverchi.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsAdapter implements UserDetails {
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
			users.add(new GrantedAuthorityImpl(role.getRolename()));
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
