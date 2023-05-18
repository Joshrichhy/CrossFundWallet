package com.example.crossfundwallet;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Services.AccountService;
import com.example.crossfundwallet.dtos.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CrossFundWalletApplicationTests {
    private UserRequest userRequest;
   @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
    }
    @Test
    void createAccount(){
        userRequest = UserRequest.builder().DOB("12/17/1994")
                .EmailAddress("kusejoshua@gmail.com")
                .firstName("Joshua")
                .lastName("Oluwakuse")
                .password("123456")
                .phoneNumber("07033490197")
                .build();
        Account account = accountService.createAccount(userRequest);
        System.out.println(account.getAccountFirstName());
        assertEquals("7033490197", account.getAccountNumber());
    }
}
