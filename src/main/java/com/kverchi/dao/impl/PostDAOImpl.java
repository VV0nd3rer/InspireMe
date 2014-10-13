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
public class PostDAOImpl extends GenericDAOImpl<Post> implements PostDAO {
		
	
	public List<Post> getSightPosts(int _sightId, int _usrId) {
		Session session = null;
		List<Post> sightPost = null;
		try {
			session = sessionFactory.openSession();
			String sQuery = "FROM Post p WHERE p.sightId =:sight_id AND p.userId =:usrId";
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
		
}
