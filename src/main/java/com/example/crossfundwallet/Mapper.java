package com.example.crossfundwallet;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.dto.request.RegisterUserRequest;
import com.example.crossfundwallet.dto.response.RegisterUserResponse;

import java.time.LocalDate;

public class Mapper {

    public static User mapUserRequest(RegisterUserRequest registerRequest){
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmailAddress(registerRequest.getEmailAddress());
        user.setPassword(registerRequest.getPassword());
        user.setDOB(registerRequest.getDOB());
        user.setDateOfRegistration(LocalDate.now());
        return user;
    }

}
