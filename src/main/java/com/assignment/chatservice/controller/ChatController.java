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
     * Registers a user for chat-room.
     * @param chatMessage
     * @param headerAccessor
     * @return
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
     * Sends a chat message to all the connected users.
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        chatService.saveMessageDetails(chatMessage);
        return chatMessage;
    }

    /**
     * Deletes the specific user chat from DB
     * @param userName
     * @return
     */
    @DeleteMapping("/user/messages/{userName}")
    public int deleteChat(@PathVariable String userName){
        return chatService.deleteMessageByUserName(userName);
    }

    /**
     * Fetch messages for specific user
     * @param userName
     * @return
     */
    @GetMapping("/fetch/user/messages/{userName}")
    public List<Message> fetchMessagesByUser(@PathVariable String userName){
        return chatService.fetchMessagesByUserName(userName);
    }

    /**
     * fetch all the messages from specific chat room
     * @return
     */
    @GetMapping("/fetch/chatroom/{chatRoom}/messages")
    public List<Message> fetchAllTheChatRoomMessages(@PathVariable String chatRoom){
        return chatService.fetchAllMessagesByChatRoom(chatRoom);
    }

    /**
     * fetch all the messages
     * @return
     */
    @GetMapping("/fetch/messages")
    public List<Message> fetchAllMessages(){
        return chatService.fetchAllMessages();
    }
}
