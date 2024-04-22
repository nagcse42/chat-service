package com.assignment.chatservice.controller;

import com.assignment.chatservice.pojos.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void testAuthenticateUser() {
        final LoginDto loginDto = new LoginDto();
        loginDto.setUsername("chatuser");
        loginDto.setPassword("chat123");
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("User signed-in successfully!.",
                HttpStatusCode.valueOf(200));
        final ResponseEntity<String> result = authController.authenticateUser(loginDto);
        Assertions.assertEquals(expectedResult, result);
    }
}
