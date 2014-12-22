package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.kverchi.dao.UserDAO;

import org.hibernate.Query;
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
   
   public User findByEmail(String email) {
	   Session session = null;
	   User res = null;

	   try {
		   session = sessionFactory.openSession();
		   String hQuery = "FROM User U WHERE U.email =:email";
		   Query query = session.createQuery(hQuery);
		   query.setParameter("email", email);
		   res = (User)query.list().get(0);
		   //res = resList.get(0);
	   } catch (Exception e) {
		   System.out.println("Error in UserDAOImpl -> findByEmail "+e.getMessage());
	   } finally {
		   if (session != null && session.isOpen()) {
               session.close();
           } 
	   }
	   return res;
   }
     
}
