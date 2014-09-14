package com.kverchi.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.Country;

@Repository
public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Country getCountry(String code) {
		Session session = null;
		Country country = null;
		try {
			session = sessionFactory.openSession();
			country = (Country)session.get(Country.class, code);
		} catch(Exception e) {
			System.out.println("Error in CountryDAOImpl->getCountry: " + e);
		} finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		return country;
	}

}
