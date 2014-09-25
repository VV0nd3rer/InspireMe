package com.kverchi.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kverchi.domain.CountrySight;;

@Service
public interface SightService {
	List<CountrySight> getAllSights(String code, Principal principal);
	public void addSight(CountrySight sight);
	public void removeSight(CountrySight sight);
	public CountrySight getSight(int sightId);
}
