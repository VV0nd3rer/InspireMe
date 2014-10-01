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
	public List<Post> getSightPosts(int _sightId, int _usrId) {
		Session session = null;
		List<Post> sightPost = null;
		try {
			session = sessionFactory.openSession();
			String sQuery = "FROM Post p WHERE p.sightId = :sight_id AND p.userId = :usrId";
			Query hQuery = session.createQuery(sQuery);
			hQuery.setParameter("sight_id", _sightId);
			hQuery.setParameter("usrId", _usrId);
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
	public void createPost(Post post) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(post);
		    tx.commit();
		} catch (Exception e) {
			System.out.println("Error in PostDAOImpl->createPost: " + e);	
			if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
	}
	public void deletePost(int _postId) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sQuery = "DELETE from Post WHERE postId = :postId";
			Query hQuery = session.createQuery(sQuery);
			hQuery.setParameter("postId", _postId);
			int res = hQuery.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error in PostDAOImpl->deletePost: " + e);
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	        }
		}
	}
	public void updatePost(Post post) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(post);
			tx.commit();
		} catch(Exception e) {
			System.out.println("Error in PostDAOImpl->updatePost: " + e);
			if (tx!=null) tx.rollback();
				e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
	               session.close();
	           }
		}
	}
}
