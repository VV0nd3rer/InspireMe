package com.kverchi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kverchi.domain.Country;

@Service
public interface CountryService {
   public Country findCountry(String code);
   public List<Country> findAllCountries();
}
