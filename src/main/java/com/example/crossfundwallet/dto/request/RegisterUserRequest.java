package com.example.crossfundwallet.dto.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    public  String DOB;
    private String password;
    private String phoneNumber;
    private String accountNumber;


}
