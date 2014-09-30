package com.kverchi.dao;

import com.kverchi.domain.Role;

public interface RoleDAO {
	public Role findByName(String rolename);
}
