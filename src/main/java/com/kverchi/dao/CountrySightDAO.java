package com.kverchi.dao;

import java.security.Principal;
import java.util.List;

import com.kverchi.domain.CountrySight;
import com.kverchi.domain.User;

public interface CountrySightDAO {

	List<CountrySight> getSightsListByCode(String code, Principal principal);
	public void addSight(CountrySight sight);
	public void removeSight(CountrySight sight);
	public CountrySight getSightById(int sightId);
}
