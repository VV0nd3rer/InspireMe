package com.kverchi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.Country;
import com.kverchi.service.CountryService;

@Repository
public class CountryServiceImpl implements CountryService {
	@Autowired 
	private CountryDAO countryDAO;
	
	public Country findCountry(String code) {
		Country country = null;
		country = countryDAO.getCountry(code);
		return country;
	}
	public List<Country> findAllCountries() {
		List<Country> countries = null;
		countries = countryDAO.getAllCountries();
		return countries;
	}
}
