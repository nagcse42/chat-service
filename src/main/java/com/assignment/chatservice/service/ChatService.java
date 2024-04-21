package com.assignment.chatservice.service;

import com.assignment.chatservice.pojos.Message;

import java.util.List;

public interface ChatService {
    List<Message> fetchMessagesByUserName(String userName);
    int deleteMessageByUserName(String userName);
    void saveMessageDetails(Message message);
    List<Message> fetchAllMessages();
}
