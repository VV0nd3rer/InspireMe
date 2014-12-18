package com.kverchi.dao;

import com.kverchi.domain.NotificationsEmails;

public interface NotificationsEmailsDAO extends GenericDAO<NotificationsEmails> {
	public NotificationsEmails getEmailData(String address);
}
