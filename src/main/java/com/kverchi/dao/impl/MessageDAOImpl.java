package com.kverchi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;






import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.kverchi.dao.MessageDAO;
import com.kverchi.domain.Message;
import com.kverchi.domain.MessageHeader;
import com.kverchi.domain.User;

@Repository
public class MessageDAOImpl extends GenericDAOImpl<Message> implements MessageDAO {

	@Override
	public List<MessageHeader> getMessagesHeaders(int userId, String status) {
		Session session = null;
		List<Object[]> tempObjList;
		List<MessageHeader> mHeaderList=new ArrayList();
		String query = "";
		
		 try {
	    	   session = sessionFactory.openSession();
	    	   session.beginTransaction();
	    if(status.equals("inbox"))	   
	    query = "SELECT messages.message_id, messages.subject, users.username, messages.date, messages.status FROM users, messages"+
	    		" WHERE users.user_id = messages.from_id AND messages.to_id=:userId AND messages.removed_by<>:userId"+
	    		" ORDER BY messages.date DESC";
	    if(status.equals("sent"))	   
		    query = "SELECT messages.message_id, messages.subject, users.username, messages.date, messages.status FROM users, messages"+
		    		" WHERE users.user_id = messages.to_id AND messages.from_id=:userId AND messages.status=0 AND messages.removed_by<>:userId"+
		    		" ORDER BY messages.date DESC";
	    if(status.equals("outbox"))	   
		    query = "SELECT messages.message_id, messages.subject, users.username, messages.date, messages.status FROM users, messages"+
		    		" WHERE users.user_id = messages.to_id AND messages.from_id=:userId AND messages.status=1 AND messages.removed_by<>:userId"+
		    		" ORDER BY messages.date DESC";
	    
	    Query querySQL = session.createSQLQuery(query)
			    		.addScalar("message_id", IntegerType.INSTANCE)
			    		.addScalar("subject", StringType.INSTANCE)
	    			  .addScalar("username", StringType.INSTANCE)
	    			  .addScalar("date",  StringType.INSTANCE)
	    			  .addScalar("status", IntegerType.INSTANCE)
	    			   .setParameter("userId", userId);
	    	  
	    	  tempObjList=querySQL.list();

	    	  for(Object[] obj: tempObjList){
	    		  MessageHeader mh= new MessageHeader();
	    		  mh.setMessageId((int)obj[0]);
	    		  mh.setSubject(obj[1].toString());
	    		  mh.setFrom(obj[2].toString());
	    		  mh.setDate(obj[3].toString());
	    		  mh.setStatus((int)obj[4]);
	    		  mHeaderList.add(mh);
	    	  }
	    	   
	    	  session.getTransaction().commit();	           
	       } catch (Exception e) {
	    	    JOptionPane.showMessageDialog(null, e.getMessage(), "Error I/O", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
		 return mHeaderList;
	}
	
	public void deleteMessageById(int userId, int messageId){
		Session session = null;
		 String query="";
	       try {
	    	   session = sessionFactory.openSession();
	           session.beginTransaction();
	           if(getById(messageId).getRemoved_by()!=0){
	        	   query = "DELETE FROM Message M WHERE M.message_id=:messageId AND (M.to_id =:userId OR M.from_id =:userId)";   
	           }
	           else{
	        	   query="UPDATE Message SET removed_by=:userId WHERE message_id=:messageId";
	           }
	        
	           Query hQuery = session.createQuery(query)
	        		   .setParameter("messageId", messageId)
	        		   .setParameter("userId", userId);
	           hQuery.executeUpdate();
	           
	         session.getTransaction().commit();
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Exception", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	}

	public Message getMessage(int userId, int messageId){
		Session session = null;
		Message message = null;
	       try {
	    	   session = sessionFactory.openSession();
	           session.beginTransaction();
	           String query = "FROM Message M WHERE M.message_id=:messageId AND (M.to_id =:userId OR M.from_id =:userId)";
	           Query hQuery = session.createQuery(query)
	        		   .setParameter("messageId", messageId)
	        		   .setParameter("userId", userId);
	        message = (Message) hQuery.list().get(0);
	        session.getTransaction().commit();
	       } catch (Exception e) {
	           JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Exception", JOptionPane.OK_OPTION);
	       } finally {
	           if (session != null && session.isOpen()) {
	               session.close();
	           }
	       }
	       return message;
	}
}
