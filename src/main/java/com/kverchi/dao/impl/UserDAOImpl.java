package com.kverchi.dao.impl;

import com.kverchi.dao.UserDAO;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.kverchi.domain.User;

@Repository	
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
  
	public User findByUsername(String username) {
	   Session session = null;
	   User res = null;
	   try {
    	   session = sessionFactory.openSession();
    	   res = (User)session.getNamedQuery("findUserByUsername")
			 .setParameter("username", username)
		     .uniqueResult();
	   } catch (Exception e) {
    	   System.out.println("Error in UserDAOImpl -> findByUsername "+e.getMessage());
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
	  return res;
   }
     
     
}
