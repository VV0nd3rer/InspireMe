package com.kverchi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kverchi.domain.User;
import com.kverchi.domain.UserDetailsAdapter;
import com.kverchi.service.UserService;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceAdapter implements UserDetailsService {
	@Autowired UserService userService;
	
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			System.out.println("no such user");
			//throw new UsernameNotFoundException("No such user: " + username);
		}  
		if (user.getRoles().isEmpty()) {
			System.out.println("has no authorities");
			//throw new UsernameNotFoundException("User " + username + " has no authorities");
		}
		if (!user.isEnabled())
			System.out.println("is disabled");
		UserDetailsAdapter principalUser = new UserDetailsAdapter(user);
		return principalUser;
	}

}
