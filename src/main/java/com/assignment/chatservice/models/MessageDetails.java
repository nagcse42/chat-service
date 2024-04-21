package com.assignment.chatservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Id
    private long id;
    private String chatRoom;
    private String userName;
    private String message;
    private LocalDateTime date;
}
