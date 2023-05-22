package com.example.crossfundwallet.dto.response;

import com.example.crossfundwallet.Data.Models.Card;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
