package com.kverchi.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.kverchi.dao.NemailAddresseDAO;
import com.kverchi.domain.NemailAddresse;
import com.kverchi.domain.User;
import com.kverchi.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired private VelocityEngine velocityEngine;
	@Autowired private NemailAddresseDAO emailAddrDAO;
	@Autowired private MessageSource messageSource;
	
	private MimeMessage createMimeMsg(Map<String, Object> model, String tempPath,
			String subject, String toEmail, String fromEmail, String fromName, MimeMessage mimeMsg) {
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tempPath, "utf-8", model);
		try {
			//In constructor the second boolean parameter is multipart - whether to create a multipart message 
			//that supports alternative texts, inline elements and attachments
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
			helper.setSubject(subject);
			helper.setTo(toEmail);
			try {
				helper.setFrom(fromEmail, fromName);
			} catch (UnsupportedEncodingException e) {
				helper.setFrom(fromEmail);
			}
			helper.setText(text, true);
		}
		catch(MessagingException e) {
			throw new RuntimeException();
		}
		return mimeMsg;
	}

	private boolean sendMsg(MimeMessage msg, Session session) {
		boolean isSended = false;
		String email = "kverchi24@gmail.com";
	    NemailAddresse emailAddr = new NemailAddresse();
	    emailAddr = emailAddrDAO.getEmailData(email);   
	    String host = "smtp.gmail.com";
	    String username = email;
	    String password = emailAddr.getPassword();
	    Transport t = null;
	    try {
	    	t = session.getTransport("smtps");
	    	t.connect(host, username, password);
	    	t.sendMessage(msg, msg.getAllRecipients());
	    	isSended = true;
	    } catch(Exception e) {
	    	
	    } finally {
	    	 if (t!=null) 
	    		 try {
	    			 t.close();
	    		 }
	    	 	 catch (Exception e) {
	    		 
	    		 }
	    }
	   return isSended;
	}
	private Properties setProperties() {
		Properties props = new Properties();
	    props.setProperty("mail.smtp.auth", "true");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.debug", "true");
	    return props;
	}
	@Override
	public boolean sendEmail(User user, String tempPath, String subject, 
						     String fromEmail, String fromName) {
		//Set JavaMail properties and create session
		Session session = Session.getInstance(setProperties());
	    MimeMessage msg = new MimeMessage(session);
	    //Set model parameters for template
	    Map<String, Object> model = new HashMap<String, Object>();
	    //Put messages from messages.properties
	    model.put("messages", this.messageSource);
	    //Put current locale
	    model.put("locale", LocaleContextHolder.getLocale());
		model.put("user", user);
		//Create MimeMessage
	    msg = createMimeMsg(model, tempPath, subject, user.getEmail(), fromEmail, fromName, msg);
	    //Send message
	    return sendMsg(msg, session);
	}
	@Override
	public boolean sendEmail(User user, String token, String tempPath, String subject, 
		     				 String fromEmail, String fromName) {
		//Set JavaMail properties and create session
		Session session = Session.getInstance(setProperties());
		MimeMessage msg = new MimeMessage(session);
		//Set model parameters for template
		Map<String, Object> model = new HashMap<String, Object>();
		//Put messages from messages.properties
	    model.put("messages", this.messageSource);
	    //Put current locale
	    model.put("locale", LocaleContextHolder.getLocale());
		model.put("user", user);
		model.put("token", token);
		//Create MimeMessage
		msg = createMimeMsg(model, tempPath, subject, user.getEmail(), fromEmail, fromName, msg);
		//Send message
		return sendMsg(msg, session);
	}
}
