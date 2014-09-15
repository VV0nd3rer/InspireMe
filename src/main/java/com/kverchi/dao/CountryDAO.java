package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Country;

public interface CountryDAO {
	public Country getCountry(String code);
	public List<Country> getAllCountries();
}
