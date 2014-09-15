package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import com.kverchi.dao.CountryDAO;
import com.kverchi.domain.Country;
import com.kverchi.domain.User;

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
	
	public List<Country> getAllCountries(){
	 Session session = null;
     List<Country> countries = new ArrayList<Country>();
     try {
  	   session = sessionFactory.openSession();
         //session = HibernateUtil.getSessionFactory().openSession();
  	 countries = session.createCriteria(Country.class).list();
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
     } finally {
         if (session != null && session.isOpen()) {
             session.close();
         }
     }
     return countries;
   }
}
