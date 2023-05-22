package com.example.crossfundwallet.dtos.response;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Data
public class RegisterUserResponse {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String DOB;
    private LocalDate DateOfRegistration;
    private String accountNumber;
}
