package com.kverchi.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.FriendDAO;
import com.kverchi.domain.Friend;
import com.kverchi.domain.User;

@Repository
public class FriendDAOImpl extends GenericDAOImpl<Friend> implements FriendDAO {
	
	@Override
	public List<Integer> getFriendsId(int userId, int status) {
		Session session = null;
		List<Integer> friendsIdResult=null;
		List<Integer> friendsIdTemp=null;
		 try {
	    	   session = sessionFactory.openSession();
	    	   session.beginTransaction();
	    	  
	    	   String query1 = "SELECT F.friendOneId FROM Friend F WHERE F.friendTwoId =:userId AND F.status =:status";
	    	   String query2 = "SELECT F.friendTwoId FROM Friend F WHERE F.friendOneId =:userId AND F.status =:status";
	    	   Query hQuery1 = session.createQuery(query1);
	    	   hQuery1.setParameter("userId", userId);
	    	   hQuery1.setParameter("status", status);
	    	   friendsIdResult = hQuery1.list();
	    	   
	    	   Query hQuery2 = session.createQuery(query2);
	    	   hQuery2.setParameter("userId", userId);
	    	   hQuery2.setParameter("status", status);
	    	   friendsIdTemp=hQuery2.list();
	    	   
	    	   friendsIdResult.addAll(friendsIdTemp);
	    	   session.getTransaction().commit();	           
	       } catch (Exception e) {
	    	    JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		 return friendsIdResult;

	}
	
	@Override
	public List<User> getPeople(int userId) {
		Session session = null;
		List<User> people = null;
	       try {
	    	   session = sessionFactory.openSession();
	           session.beginTransaction();
	           String query = "FROM User U WHERE U.userId <>:userId";
	           Query hQuery = session.createQuery(query);
	    	   hQuery.setParameter("userId", userId);
	    	   people = hQuery.list();
	           session.getTransaction().commit();
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Exception", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return people;
	}
	
	@Override
	public List<User> getPeople(int userId, String fragment) {
		Session session = null;
		List<User> people = null;
	       try {
	    	   session = sessionFactory.openSession();
	           session.beginTransaction();
	           String query = "FROM User U WHERE U.userId <>:userId AND U.username like :fragment";
	           Query hQuery = session.createQuery(query);
	    	   hQuery.setParameter("userId", userId);
	    	   hQuery.setParameter("fragment", "%"+fragment+"%");
	    	   people = hQuery.list();
	           session.getTransaction().commit();
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Exception", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return people;
	}

	
}
