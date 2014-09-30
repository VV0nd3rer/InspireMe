package com.kverchi.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.RoleDAO;
import com.kverchi.domain.Role;
import com.kverchi.domain.User;

@Repository
public class RoleDAOImpl implements RoleDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Role findByName(String rolename) {
		
		Session session = null;
		Role res = null;
		try {
	    	   session = sessionFactory.openSession();
	    	   res = (Role)session.getNamedQuery("findRoleByName")
	    			 .setParameter("rolename", rolename)
	    			 .uniqueResult();
		} catch (Exception e) {
	    	   System.out.println("Error in RoleDAOImpl -> findByName "+e.getMessage());
	    } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	    }
		return res;
	}
	
}
