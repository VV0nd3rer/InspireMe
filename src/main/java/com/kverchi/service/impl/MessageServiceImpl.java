package com.kverchi.service.impl;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kverchi.dao.MessageDAO;
import com.kverchi.domain.Message;
import com.kverchi.domain.MessageHeader;
import com.kverchi.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired 
	private MessageDAO messageDAO;
	
	public List<MessageHeader>getHeaders(int userId, String status){
		return messageDAO.getMessagesHeaders(userId, status);
	}

	public void deleteMessage(int userId, int messageId) {
		messageDAO.deleteMessageById(userId, messageId);
	}

	@Override
	public Message getMessageById(int userId, int messageId) {
		return messageDAO.getMessage(userId,messageId);
	}

	@Override
	public void markAsRead(Message message) {
		message.setStatus(1);
		messageDAO.update(message);
	}

	@Override
	public void sendMessage(Message message) {
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
		message.setDate(sdf.format(curDate));
		message.setStatus(0);
		message.setRemoved_by(0);
		messageDAO.create(message);
	}
	

}
