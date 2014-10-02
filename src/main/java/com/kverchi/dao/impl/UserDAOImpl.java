package com.kverchi.dao.impl;

import com.kverchi.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kverchi.domain.User;

@Repository	
public class UserDAOImpl implements UserDAO {
   @Autowired
   private SessionFactory sessionFactory;
   @Autowired
   private PasswordEncoder passwordEncoder;

   public void addUser(User user) {
	   Session session = null;
       try {
    	   String password = user.getPassword();
    	   user.setPassword(passwordEncoder.encode(password));
    	   user.setEnabled(true);
    	   session = sessionFactory.openSession();
    	   user.printUser();
           //session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.save(user);
           session.getTransaction().commit();
       } catch (Exception e) {
    	   System.out.println("Error in addUser "+e.getMessage());
           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
   }
   
   public void updateUser(User user) {
       Session session = null;
       try {
    	   session = sessionFactory.openSession();
           //session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.update(user);
           session.getTransaction().commit();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
 }
   
 public User getUserById(int id) {
	   
       Session session = null;
       User user = null;
       try {
    	   session = sessionFactory.openSession();
    	   user = (User) session.get(User.class, id);
       } catch (Exception e) {
    	   System.out.println("Error in getUserByLogin "+e.getMessage());
           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return user;
 }
   
 
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
   public List<User> getAllUsers() {
       Session session = null;
       List<User> users = new ArrayList<User>();
       try {
    	   session = sessionFactory.openSession();
           //session = HibernateUtil.getSessionFactory().openSession();
           users = session.createCriteria(User.class).list();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
       return users;
 }
   
 public void deleteUser(User user) {
       Session session = null;
       try {
    	   session = sessionFactory.openSession();
           //session = HibernateUtil.getSessionFactory().openSession();
           session.beginTransaction();
           session.delete(user);
           session.getTransaction().commit();
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
 }
   
  /* public void addRole(int role_id, int userId) {
		Session session = null;
		UserRole role = new UserRole();
		  try {
			  session = sessionFactory.openSession();
	    	  role.setRole_id(role_id);
	    	  role.setUserId(userId);
	    	  //System.out.println("role_id: " + role_id + "username: " + username);
	    	  session.beginTransaction();
	          session.save(role);
	          session.getTransaction().commit();
		  } catch(Exception e) {
			  System.out.println("Error in Role findByName "+e.getMessage());
		  } finally {
			  if (session != null && session.isOpen()) {
	               session.close();
	           }
		  }
	}*/
   
     
}
