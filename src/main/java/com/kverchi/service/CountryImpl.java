package com.kverchi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.Country;

@Repository
public class CountryImpl implements CountryService {
	@Autowired 
	private CountryDAO countryDAO;
	
	public Country findCountry(String code) {
		Country country = null;
		country = countryDAO.getCountry(code);
		return country;
	}

}
