package com.assignment.chatservice.controller;

import com.assignment.chatservice.pojos.Message;
import com.assignment.chatservice.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {
    @InjectMocks
    private ChatController chatController;

    @Mock
    private ChatServiceImpl chatService;

    @Test
    void testFetchAllMessages_ChatServiceReturnsNoItems() {
        when(chatService.fetchAllMessages()).thenReturn(Collections.emptyList());
        final List<Message> result = chatController.fetchAllMessages();
        Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testDeleteChat() {
        when(chatService.deleteMessageByUserName("userName")).thenReturn(0);
        final int result = chatController.deleteChat("userName");
        assertEquals(0, result);
    }

    @Test
    void testFetchAllMessages() {
        final List<Message> expectedResult = List.of(Message.builder()
                .content("content")
                .sender("sender")
                .build());
        final List<Message> messages = List.of(Message.builder()
                .content("content")
                .sender("sender")
                .build());
        when(chatService.fetchAllMessages()).thenReturn(messages);
        final List<Message> result = chatController.fetchAllMessages();
        assertEquals(expectedResult, result);
    }

    @Test
    void testSendMessage() {
        final Message chatMessage = Message.builder()
                .content("Hello")
                .sender("tom")
                .build();
        final Message expectedResult = Message.builder()
                .content("Hello")
                .sender("tom")
                .build();
        final Message result = chatController.sendMessage(chatMessage);
        assertEquals(expectedResult, result);
        verify(chatService).saveMessageDetails(Message.builder()
                .content("Hello")
                .sender("tom")
                .build());
    }

    @Test
    void testFetchMessagesByUser() {
        final List<Message> expectedResult = List.of(Message.builder()
                .content("Hello")
                .sender("Nag")
                .build());
        final List<Message> messages = List.of(Message.builder()
                .content("Hello")
                .sender("Nag")
                .build());
        when(chatService.fetchMessagesByUserName("userName")).thenReturn(messages);
        final List<Message> result = chatController.fetchMessagesByUser("userName");
        assertEquals(expectedResult, result);
    }

    @Test
    void testFetchAllTheChatRoomMessages() {
        final List<Message> expectedResult = List.of(Message.builder()
                .content("Hello")
                .sender("Nag")
                .build());
        final List<Message> messages = List.of(Message.builder()
                .content("Hello")
                .sender("Nag")
                .build());
        when(chatService.fetchAllMessagesByChatRoom("chatRoom")).thenReturn(messages);

        // Run the test
        final List<Message> result = chatController.fetchAllTheChatRoomMessages("chatRoom");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
