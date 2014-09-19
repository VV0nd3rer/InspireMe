package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.CountrySight;
import com.kverchi.domain.User;

public interface CountrySightDAO {

	List<CountrySight> getSightsListByCode(String code);
	public void addSight(CountrySight sight);
}
