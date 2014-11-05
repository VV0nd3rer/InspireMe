package com.kverchi.service;

import com.kverchi.domain.User;

public interface EmailService {
	public boolean sendEmail(User user, String tempPath, String subject, 
		     				 String fromEmail, String fromName);
	public boolean sendEmail(User user, String token, String tempPath, String subject, 
		     				 String fromEmail, String fromName);
}
