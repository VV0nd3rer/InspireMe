package com.kverchi.service;

import org.springframework.stereotype.Service;

import com.kverchi.domain.Country;

@Service
public interface CountryService {
   public Country findCountry(String code);
}
