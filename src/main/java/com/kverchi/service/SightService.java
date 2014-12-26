package com.kverchi.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kverchi.domain.CountrySight;;

@Service
public interface SightService {
	List<CountrySight> getAllSights(String code, int userId);
	public boolean addSight(CountrySight sight, MultipartFile img);
	public void removeSight(CountrySight sight);
	public CountrySight getSight(int sightId);
}
