package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.Exception.EmailExistException;
import com.example.crossfundwallet.Exception.PhoneNumberExistException;
import com.example.crossfundwallet.dto.request.RegisterUserRequest;
import com.example.crossfundwallet.dto.response.RegisterUserResponse;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
     private UserService userService;

     private RegisterUserRequest registerRequest;

    @BeforeEach
    public void setUserService(){

    }
//    @Test
//    void contextLoads() {
//    }


    @Test
     void register() throws EmailExistException, PhoneNumberExistException {
        registerRequest = RegisterUserRequest.builder().DOB("01/12/1923")
                .firstName("Ola")
                .lastName("Yinka")
                .emailAddress("myemail@gmail.com")
                .phoneNumber("0901234568")
                .build();
        RegisterUserResponse user = userService.register(registerRequest);
        System.out.println(user.getAccountNumber());
        assertEquals("Yinka", user.getLastName());


    }

}