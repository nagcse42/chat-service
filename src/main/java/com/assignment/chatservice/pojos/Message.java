package com.assignment.chatservice.pojos;

import com.assignment.chatservice.constants.MessageType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @NotBlank(message = "content missing")
    private String content;
    @NotBlank(message = "sender missing")
    private String sender;
    @NotBlank(message = "message type missing")
    private MessageType type;
}
