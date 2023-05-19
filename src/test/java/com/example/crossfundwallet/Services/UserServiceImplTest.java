package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.Exception.EmailExistException;
import com.example.crossfundwallet.Exception.PhoneNumberExistException;
import com.example.crossfundwallet.dto.request.RegisterUserRequest;
import com.example.crossfundwallet.dto.response.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;
    @Autowired
    RegisterUserRequest registerRequest;

    @BeforeEach
    public void setUserService(){
        RegisterUserRequest registerRequest = new RegisterUserRequest();
        registerRequest.setFirstName("Ola");
        registerRequest.setLastName("Yinka");
        registerRequest.setEmailAddress("olayi@email.com");
        registerRequest.setPassword("passsword1");
        registerRequest.setPhoneNumber("0901234568");
        registerRequest.setAccountNumber("0901234568");
        registerRequest.setDOB(String.valueOf(LocalDate.parse("1998-01-01")));
    }


    @Test
    void register() throws EmailExistException, PhoneNumberExistException {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmailAddress(registerRequest.getEmailAddress());
        user.setPassword(registerRequest.getPassword());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAccountNumber(registerRequest.getPhoneNumber());
        RegisterUserResponse regUser =userService.register(registerRequest);
        System.out.println(regUser);
        assertNotNull(regUser);
//        assertEquals(user, registerRequest);
    }

}