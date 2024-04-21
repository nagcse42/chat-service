package com.assignment.chatservice.service.impl;

import com.assignment.chatservice.constants.MessageType;
import com.assignment.chatservice.models.MessageDetails;
import com.assignment.chatservice.pojos.Message;
import com.assignment.chatservice.repository.MessageDetailsRepository;
import com.assignment.chatservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MessageDetailsRepository messageDetailsRepository;

    @Override
    public List<Message> fetchMessagesByUserName(String userName) {
        return transformMessageDetailsToMessages(messageDetailsRepository.findByUserName(userName));
    }

    @Override
    public int deleteMessageByUserName(String userName) {
        return messageDetailsRepository.deleteByUserName(userName);
    }

    @Override
    public void saveMessageDetails(Message message) {
        messageDetailsRepository.save(transformMessageToMessageDetails(message));
    }

    private MessageDetails transformMessageToMessageDetails(Message message) {
        MessageDetails messageDetails = new MessageDetails();
        messageDetails.setMessage(message.getContent());
        messageDetails.setUserName(message.getSender());
        messageDetails.setChatRoom("DEFAULT");
        return messageDetails;
    }

    private List<Message> transformMessageDetailsToMessages(List<MessageDetails> messageDetailsList){
        if(CollectionUtils.isEmpty(messageDetailsList)) {
            return Collections.emptyList();
        }

        List<Message> messages = new ArrayList<>();
        for (MessageDetails messageDetails: messageDetailsList) {
            Message message = new Message();
            message.setContent(messageDetails.getMessage());
            message.setType(MessageType.CHAT);
            message.setSender(messageDetails.getUserName());
            messages.add(message);
        }

        return messages;
    }
}
