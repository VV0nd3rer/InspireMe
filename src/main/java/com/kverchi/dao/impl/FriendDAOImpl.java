package com.kverchi.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
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
	    	  
	    	   String query1 = "SELECT F.friend_one_id FROM Friend F WHERE F.friend_two_id =:userId AND F.status =:status";
	    	   String query2 = "SELECT F.friend_two_id FROM Friend F WHERE F.friend_one_id =:userId AND F.status =:status";
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
	
	public List<User> getFriends(int userId, int status) {
		Session session = null;
		List<User> friendsResult=null;
		
		 try {
	    	   session = sessionFactory.openSession();
	    	   session.beginTransaction();
	    String query = "SELECT users.user_id, users.username, users.email, users.password, users.enabled FROM users, users_friends"+
" WHERE  ((users.user_id = users_friends.friend_two_id AND users_friends.friend_one_id=:curUsrId)"+
" OR (users.user_id = users_friends.friend_one_id AND users_friends.friend_two_id=:curUsrId))"+
" AND users_friends.status=:status";
	    	  Query querySQL = session.createSQLQuery(query).addEntity(User.class)
	    			   .setParameter("curUsrId", userId).setParameter("status", status);
	    	  
	    	  friendsResult = querySQL.list();
	    	  session.getTransaction().commit();	           
	       } catch (Exception e) {
	    	    JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		 return friendsResult;
	}
	
}
