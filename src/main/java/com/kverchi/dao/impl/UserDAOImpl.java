package com.kverchi.dao.impl;

import com.kverchi.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kverchi.domain.User;
import com.kverchi.domain.UserRole;

@Repository	
public class UserDAOImpl implements UserDAO {
  
   private SessionFactory sessionFactory;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   public void setSession(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
   }
   
   public void addUser(User user) {
	   Session session = null;
       try {
    	   String password = user.getPassword();
    	   user.setPassword(passwordEncoder.encode(password));
    	   user.setEnabled(1);
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
	   //System.out.println("login to getUserByLogin: " + login);
       Session session = null;
       User user = null;
       try {
    	   session = sessionFactory.openSession();
    	   /*Query q = session.getNamedQuery("findByUsername");
    	   q.setParameter("username", login);*/
    	   //user = (User) q.uniqueResult();
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
   
  /* public User getUserByPassword(User user) {
	   User res = getUserByLogin(user.getUsername());
	   String cr_userPass = passwordEncoder.encode(user.getPassword());
	   System.out.println(res.getPassword() + " : " + cr_userPass);
	   if(res.getPassword().equals(cr_userPass))
		   return res;
	   return null;
   }*/
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
   
   public void addRole(int role_id, int userId) {
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
	}
   
     
}
