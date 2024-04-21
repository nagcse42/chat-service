package com.assignment.chatservice.service.impl;

import com.assignment.chatservice.constants.MessageType;
import com.assignment.chatservice.models.MessageDetails;
import com.assignment.chatservice.pojos.Message;
import com.assignment.chatservice.repository.MessageDetailsRepository;
import com.assignment.chatservice.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MessageDetailsRepository messageDetailsRepository;

    @Override
    public List<Message> fetchMessagesByUserName(String userName) {
        log.info("fetching messages for the user: {} started", userName);
        return transformMessageDetailsToMessages(messageDetailsRepository.findByUserName(userName));
    }

    @Override
    public int deleteMessageByUserName(String userName) {
        log.info("delete messages for the user: {} started", userName);
        return messageDetailsRepository.deleteByUserName(userName);
    }

    @Override
    public void saveMessageDetails(Message message) {
        log.info("saveMessageDetails details : {} started", message);
        messageDetailsRepository.save(transformMessageToMessageDetails(message));
    }

    @Override
    public List<Message> fetchAllMessagesByChatRoom(String chatRoom) {
        log.info("fetching all the messages for the chatRoom: {} started", chatRoom);
        return transformMessageDetailsToMessages(messageDetailsRepository.findByChatRoom(chatRoom));
    }

    @Override
    public List<Message> fetchAllMessages() {
        log.info("fetching all the messages started");
        return transformMessageDetailsToMessages(messageDetailsRepository.findAll());
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
