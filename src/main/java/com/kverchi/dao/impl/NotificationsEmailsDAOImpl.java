package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kverchi.dao.NotificationsEmailsDAO;
import com.kverchi.domain.NotificationsEmails;

@Repository
public class NotificationsEmailsDAOImpl extends GenericDAOImpl<NotificationsEmails> implements NotificationsEmailsDAO {

	@Override
	public NotificationsEmails getEmailData(String address) {
		Session session = null;
		List<NotificationsEmails> temp = new ArrayList();
		NotificationsEmails res = new NotificationsEmails();
		try {
			session = sessionFactory.openSession();
	    	session.beginTransaction();
	    	String query = "FROM NemailAddresse E where E.email =:email";
	    	Query hQuery = session.createQuery(query);
	    	hQuery.setParameter("email", address);
	    	temp = hQuery.list();
	    	res = temp.get(0);
	    	session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in NemailsAddressesDAOImpl -> getEmailData: " + e);
		} finally {
			 if (session != null && session.isOpen()) {
	               session.close();
	         }
		}
		return res;
	}

}
