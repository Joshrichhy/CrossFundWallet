package com.example.crossfundwallet.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String emailAddress;
    public  String DOB;
    private String password;
    private String phoneNumber;
    private String accountNumber;


}
