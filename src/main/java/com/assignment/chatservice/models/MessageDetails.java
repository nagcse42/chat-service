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
@Table(name="MESSAGE_DETAILS")
public class MessageDetails {
    private long id;
    private long chatRoomId;
    private String userName;
    private String message;
    private LocalDateTime date;
}
