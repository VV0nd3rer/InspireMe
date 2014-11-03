package com.kverchi.dao;

import java.util.List;

import com.kverchi.domain.Message;
import com.kverchi.domain.MessageHeader;

public interface MessageDAO extends GenericDAO<Message> {
		List<MessageHeader> getMessagesHeaders(int userId, String status);
	    void deleteMessageById (int userId, int messageId);
	    Message getMessage(int userId, int messageId);
}
