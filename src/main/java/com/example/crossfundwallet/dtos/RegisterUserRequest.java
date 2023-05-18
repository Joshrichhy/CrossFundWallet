package com.example.crossfundwallet.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String EmailAddress;
    private String DOB;
    private String password;
    private String phoneNumber;


}
