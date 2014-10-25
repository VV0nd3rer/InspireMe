package com.kverchi.service;

import com.kverchi.domain.User;

public interface EmailService {
	public void sendEmail(User user, String tempPath,
			String subject, String toEmail, String fromEmail, String fromName);
}
