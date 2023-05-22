package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Exceptions.EmailExistException;
import com.example.crossfundwallet.Exceptions.PhoneNumberExistException;
import com.example.crossfundwallet.dtos.request.RegisterUserRequest;
import com.example.crossfundwallet.dtos.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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