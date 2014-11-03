package com.kverchi.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.kverchi.dao.GenericDAO;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

	@Autowired
	   protected SessionFactory sessionFactory;

    private Class<T> type;
    
   
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }
    
	@Override
	public void create(final T t) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(t);
		    tx.commit();
		} catch (Exception e) {
			System.out.println("Error in GenericDAOImpl->create: " + e);	
			if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(final Serializable id) {
		
		Session session = null;
		T obj = null;
		try {
			session = sessionFactory.openSession();
			obj = (T) session.get(type,id);
		} catch(Exception e) {
			
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
		return obj;
	}

	@Override
	public void update(final T t) {
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} catch(Exception e) {
			
			
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
		
	}

	@Override
	public void delete(final T t) {
		Session session = null;
	
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			System.out.println("Error in PostDAOImpl->createPost: " + e);	
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	        }
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllRecords() {
		Session session = null;
		List<T> objList = null;
		 try {
	    	   session = sessionFactory.openSession();
	    	   objList = session.createCriteria(type).list();
	       } catch (Exception e) {
	    	  	           JOptionPane.showMessageDialog(null, e.getMessage(), "Exception I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		return objList;
	}

}
