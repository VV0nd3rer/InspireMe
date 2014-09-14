package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.CountriesSights;

public interface CountriesSightsDAO {

	List<CountriesSights> getItemListByCode(String code);
}
