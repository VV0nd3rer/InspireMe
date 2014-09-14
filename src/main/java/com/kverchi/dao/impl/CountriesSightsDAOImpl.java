package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;




import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.CountriesSightsDAO;
import com.kverchi.domain.CountriesSights;


@Service
public class CountriesSightsDAOImpl implements CountriesSightsDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<CountriesSights> getItemListByCode(String code) {
	       Session session = null;
	       List<CountriesSights> sights = null;
	       try {
	    	   session = sessionFactory.openSession();
	    	   String query = " FROM CountriesSights cs WHERE cs.country_code = :code";
	    	
	    	   Query hQuery = session.createQuery(query);
	    	   hQuery.setParameter("code", code);
	    	   
	           sights = hQuery.list();
	          
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return sights;
	 }
	
}
