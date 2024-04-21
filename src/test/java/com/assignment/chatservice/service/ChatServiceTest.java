package com.assignment.chatservice.service;

import com.assignment.chatservice.constants.MessageType;
import com.assignment.chatservice.models.MessageDetails;
import com.assignment.chatservice.pojos.Message;
import com.assignment.chatservice.repository.MessageDetailsRepository;
import com.assignment.chatservice.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {
    @InjectMocks
    private ChatServiceImpl chatService;

    @Mock
    private MessageDetailsRepository messageDetailsRepo;

    @Test
    void test_fetchAllMessagesByChatRoom() {
        Mockito.when(messageDetailsRepo.findByChatRoom(Mockito.isA(String.class)))
                        .thenReturn(mockMessageDetails());
        List<Message> messages = chatService.fetchAllMessagesByChatRoom("DEFAULT");
        Mockito.verify(messageDetailsRepo, Mockito.times(1)).findByChatRoom(Mockito.isA(String.class));
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(1, messages.size());
    }

    @Test
    void test_fetchAllMessages() {
        Mockito.when(messageDetailsRepo.findAll()).thenReturn(mockMessageDetails());
        List<Message> messages = chatService.fetchAllMessages();
        Mockito.verify(messageDetailsRepo, Mockito.times(1)).findAll();
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(1, messages.size());
    }

    @Test
    void test_fetchMessagesByUserName() {
        Mockito.when(messageDetailsRepo.findByUserName(Mockito.isA(String.class))).thenReturn(mockMessageDetails());
        List<Message> messages = chatService.fetchMessagesByUserName("Nag");
        Mockito.verify(messageDetailsRepo, Mockito.times(1)).findByUserName(Mockito.isA(String.class));
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(1, messages.size());
        Mockito.when(messageDetailsRepo.findByUserName(Mockito.isA(String.class))).thenReturn(null);
        messages = chatService.fetchMessagesByUserName("Nag");
        Assertions.assertNotNull(messages);
        Assertions.assertEquals(0, messages.size());
    }

    @Test
    void test_saveMessageDetails() {
        Message message = new Message();
        message.setContent("Hey Team");
        message.setSender("Nag");
        message.setType(MessageType.CHAT);
        chatService.saveMessageDetails(message);
        Mockito.verify(messageDetailsRepo, Mockito.times(1)).save(Mockito.isA(MessageDetails.class));
    }

    @Test
    void test_deleteChatBy() {
        Mockito.when(messageDetailsRepo.deleteByUserName(Mockito.isA(String.class))).thenReturn(1);
        int deletedRecords = chatService.deleteMessageByUserName("Nag");
        Mockito.verify(messageDetailsRepo, Mockito.times(1)).deleteByUserName(Mockito.isA(String.class));
        Assertions.assertEquals(1, deletedRecords);
    }

    private List<MessageDetails> mockMessageDetails() {
        MessageDetails messageDetails = new MessageDetails();
        messageDetails.setChatRoom("DEFAULT");
        messageDetails.setMessage("Hey All");
        messageDetails.setUserName("Nag");

        List<MessageDetails> messageDetailsList = new ArrayList<>();
        messageDetailsList.add(messageDetails);
        return messageDetailsList;
    }
}
