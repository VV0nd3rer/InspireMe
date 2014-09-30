package com.kverchi.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.CountrySightDAO;
import com.kverchi.domain.CountrySight;
import com.kverchi.service.SightService;

@Repository
public class SightServiceImpl implements SightService {
	@Autowired 
	private CountrySightDAO sightDAO;

	public List<CountrySight> getAllSights(String code, int userId) {
		return sightDAO.getSightsListByCode(code, userId);
	}

	public void addSight(CountrySight sight) {
		sightDAO.addSight(sight);
	}

	public void removeSight(CountrySight sight) {
		sightDAO.removeSight(sight);
	}

	public CountrySight getSight(int sightId) {
		return sightDAO.getSightById(sightId);
	}
}
