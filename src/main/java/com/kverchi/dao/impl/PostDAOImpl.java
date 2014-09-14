package com.kverchi.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.PostDAO;
import com.kverchi.domain.Post;
import com.kverchi.domain.User;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired 
	private SessionFactory sessionFactory;
	
	public Post getPost(int _id) {
		System.out.println("fix me!");
		return null;
	}

	public List<Post> getAllPosts() {
		Session session = null;
		List<Post> posts = null;
		 try {
	    	   session = sessionFactory.openSession();
	    	   posts = session.createCriteria(Post.class).list();
	       } catch (Exception e) {
	    	   System.out.println("Error in PostDAOImpl->getAllPosts "+e.getMessage());
	           JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		return posts;
	}

}
