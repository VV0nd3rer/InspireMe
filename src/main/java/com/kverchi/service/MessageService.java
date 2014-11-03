package com.kverchi.service;

import java.util.List;

import com.kverchi.domain.Message;
import com.kverchi.domain.MessageHeader;

public interface MessageService {
List<MessageHeader>getHeaders(int userId, String status);
void deleteMessage(int userId, int messageId);
Message getMessageById(int userId, int messageId);
void markAsRead(Message message);
void sendMessage(Message message);
}
