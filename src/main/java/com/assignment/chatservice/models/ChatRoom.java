package com.assignment.chatservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="CHAT_ROOM")
public class ChatRoom {
    private long id;
    private String name;
    private String createdBy;
    private LocalDateTime createdDate;
    private String status;
}
