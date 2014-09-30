package com.kverchi.dao.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.CountrySightDAO;
import com.kverchi.domain.CountrySight;
import com.kverchi.domain.User;


@Service
public class CountrySightDAOImpl implements CountrySightDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<CountrySight> getSightsListByCode(String code, int userId) {
	       Session session = null;
	       List<CountrySight> sights = null;
	       try {
	    	   session = sessionFactory.openSession();
	    	   String query = " FROM CountrySight cs WHERE cs.country_code = :code"
	    	   		+ " AND cs.userId=:userId";
	    	   Query hQuery = session.createQuery(query);
	    	   hQuery.setParameter("code", code);
	    	   hQuery.setParameter("userId", userId);   
	           sights = hQuery.list();
	          
	       } 
	       catch (Exception e) {
	    	   System.out.println("Error in getSightsListByCode "+e.getMessage());
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } 
	       finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return sights;
	 }
	
	 public void addSight(CountrySight sight) {
		   Session session = null;
	       try {
	    	   session = sessionFactory.openSession();
	    	   session.beginTransaction();
	           session.save(sight);
	           session.getTransaction().commit();
	       } 
	       catch (Exception e) {
	    	   System.out.println("Error in addSight "+e.getMessage());
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } 
	       finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	   }
	 
	 public void removeSight(CountrySight sight) {
		 Session session = null;
	       try {
	    	   session = sessionFactory.openSession();
	           
	           session.beginTransaction();
	           session.delete(sight);
	           session.getTransaction().commit();
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	 }
	 
	 public CountrySight getSightById(int sightId) {
		   
	       Session session = null;
	       CountrySight sight = null;
	       try {
	    	   session = sessionFactory.openSession();
	    	   sight = (CountrySight) session.get(CountrySight.class, sightId);
	       } catch (Exception e) {
	    	   System.out.println("Error in getSightById "+e.getMessage());
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return sight;
	 }
	   
	
}
