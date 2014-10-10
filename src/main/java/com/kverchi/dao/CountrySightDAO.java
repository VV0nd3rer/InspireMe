package com.kverchi.dao;

import java.security.Principal;
import java.util.List;

import com.kverchi.domain.CountrySight;
import com.kverchi.domain.User;

public interface CountrySightDAO extends GenericDAO<CountrySight>{

	List<CountrySight> getSightsListByCode(String code, int userId);
	
}
