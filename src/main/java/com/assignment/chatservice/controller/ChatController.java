package com.assignment.chatservice.controller;

import com.assignment.chatservice.pojos.Message;
import com.assignment.chatservice.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ChatController {

    @Autowired
    private ChatService chatService;

    /**
     * Registers a user for chat.
     *
     * param chatMessage The chat message containing the sender's information.
     * param headerAccessor The SimpMessageHeaderAccessor object used to access session attributes.
     * return The registered chat message.
     */
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatMessage.setContent(chatMessage.getSender()+" Joined the chat");
        chatService.saveMessageDetails(chatMessage);
        return chatMessage;
    }

    /**
     * Sends a chat message to all connected users.
     *
     * param chatMessage The chat message to be sent.
     * return The sent chat message.
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        chatService.saveMessageDetails(chatMessage);
        return chatMessage;
    }
    @DeleteMapping("/user/messages/{userName}")
    public int deleteChat(@PathVariable String userName){
        return chatService.deleteMessageByUserName(userName);
    }

    @GetMapping("/fetch/user/messages/{userName}")
    public List<Message> fetchMessagesByUser(@PathVariable String userName){
        return chatService.fetchMessagesByUserName(userName);
    }

    @GetMapping("/fetch/messages")
    public List<Message> fetchAllMessages(@PathVariable String userName){
        return chatService.fetchMessagesByUserName(userName);
    }
}
