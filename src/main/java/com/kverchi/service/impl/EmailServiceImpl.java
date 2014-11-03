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
	
	/*@Autowired
	private JavaMailSender mailSender;*/
	@Autowired private VelocityEngine velocityEngine;
	@Autowired private NemailAddresseDAO emailAddrDAO;
	
	private MimeMessage createEmail(User user, String tempPath,
			String subject, String toEmail, String fromEmail, String fromName, MimeMessage mimeMsg) {
		//mailSender.createMimeMessage();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, tempPath, "utf-8", model);
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
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

	@Override
	public void sendEmail(User user, String tempPath, String subject, 
						  String toEmail, String fromEmail, String fromName) {
		String email = "kverchi24@gmail.com";
	    NemailAddresse emailAddr = new NemailAddresse();
	    emailAddr = emailAddrDAO.getEmailData(email);
	    //final String password = emailAddr.getEmail();
	    
	    String host = "smtp.gmail.com";
	    String username = email;
	    String password = emailAddr.getPassword();
	    Properties props = new Properties();
	    props.setProperty("mail.smtp.auth", "true");
	    props.setProperty("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.debug", "true");
	    Session session = Session.getInstance(props);
	    MimeMessage msg = new MimeMessage(session);
	    msg = createEmail(user, tempPath, subject, toEmail, fromEmail, fromName, msg);
	    Transport t = null;
	    try {
	    	t = session.getTransport("smtps");
	    	t.connect(host, username, password);
	    	t.sendMessage(msg, msg.getAllRecipients());
	    } catch(Exception e) {
	    	
	    } finally {
	    	 if (t!=null) 
	    		 try {
	    			 t.close();
	    		 }
	    	 	 catch (Exception e) {
	    		 
	    		 }
	    }
	   
	}
}
