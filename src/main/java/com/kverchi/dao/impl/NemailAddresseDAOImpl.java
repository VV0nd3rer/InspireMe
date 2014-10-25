package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.kverchi.dao.NemailAddresseDAO;
import com.kverchi.domain.NemailAddresse;

@Service
public class NemailAddresseDAOImpl extends GenericDAOImpl<NemailAddresse> implements NemailAddresseDAO{

	@Override
	public NemailAddresse getEmailData(String address) {
		Session session = null;
		List<NemailAddresse> temp = new ArrayList();
		NemailAddresse res = new NemailAddresse();
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
