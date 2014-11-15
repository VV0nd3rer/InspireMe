package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.PageContentDAO;
import com.kverchi.domain.MessageHeader;

@Repository
public class PageContentDAOImpl implements PageContentDAO {
	@Autowired
	   protected SessionFactory sessionFactory;
	
	@Override
	public HashMap<String, String> getPageContent(String pageId, String lang) {
			Session session = null;
			List<Object[]> tempObjList;
			HashMap<String, String> result = new HashMap<String, String>();
					
			 try {
		    	   session = sessionFactory.openSession();
		    	   session.beginTransaction();
		    
		    	   String query = "SELECT content_keys.label, content.content"+
							" FROM content, content_keys, pages_content, pages"+
							" WHERE content_keys.key_id = content.key_id AND content_keys.key_id = pages_content.key_id"+
							" AND pages.page_id = pages_content.page_id"+
							" AND pages.page_id =:pageId AND content.lang_code =:lang";
		    Query querySQL = session.createSQLQuery(query)
				    		.addScalar("label", StringType.INSTANCE)
				    		.addScalar("content", StringType.INSTANCE)
		    			    .setParameter("pageId", pageId)
		    			    .setParameter("lang", lang);
		    	  
		    	  tempObjList=querySQL.list();
		    	  
		    	  for(Object[] obj: tempObjList){
		    		  result.put(obj[0].toString(), obj[1].toString());
		    	  }
		    	   
		    	  session.getTransaction().commit();	           
		       } catch (Exception e) {
		    	    JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
		       } finally {
		           if (session != null && session.isOpen()) {
		               session.close();
		           }
		       }
		
		return result;
	}

}
