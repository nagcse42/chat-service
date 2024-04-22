package com.assignment.chatservice.pojos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "username missing")
    private String username;
    @NotBlank(message = "password missing")
    private String password;
}