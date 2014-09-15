package com.kverchi.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.PostDAO;
import com.kverchi.domain.Post;
import com.kverchi.domain.User;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired 
	private SessionFactory sessionFactory;
	
	public Post getPost(int _postId) {
		Session session = null;
		Post post = null;
		try {
			session = sessionFactory.openSession();
			post = (Post) session.get(Post.class, _postId);
		} catch(Exception e) {
			System.out.println("Error in PostDAOImpl->getPost: " + e);
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
		return post;
	}
	public List<Post> getSightPosts(int _sightId) {
		Session session = null;
		List<Post> sightPost = null;
		try {
			session = sessionFactory.openSession();
			String sQuery = "FROM Post p WHERE p.sightId = :sight_id";
			Query hQuery = session.createQuery(sQuery);
			hQuery.setParameter("sight_id", _sightId);
			sightPost = hQuery.list();
		} catch(Exception e) {
			System.out.println("Error in PostDAOImpl->getSigthPosts: " + e);
		} finally {
			 if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
		
		return sightPost;
	}
	//Not use now
	public List<Post> getAllPosts() {
		Session session = null;
		List<Post> posts = null;
		 try {
	    	   session = sessionFactory.openSession();
	    	   posts = session.createCriteria(Post.class).list();
	       } catch (Exception e) {
	    	   System.out.println("Error in PostDAOImpl->getAllPosts "+e.getMessage());
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Îøèáêà I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		return posts;
	}
	public void addPost(Post post) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//!!! Set sight id from selected sight
			post.setSightId(2);
			System.out.println("In addPost : " + post.getText());
			session.save(post);
		    tx.commit();
		} catch (Exception e) {
			System.out.println("Error in PostDAOImpl->addPost: " + e);	
			if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
	}
}
