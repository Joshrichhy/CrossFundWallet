package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.dtos.DepositRequest;
import com.example.crossfundwallet.dtos.RegisterUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {
    private RegisterUserRequest registerUserRequest;
    private  Account account;
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        registerUserRequest = RegisterUserRequest.builder().DOB("12/17/1994")
                .EmailAddress("kusejoshua@gmail.com")
                .firstName("Joshua")
                .lastName("Oluwakuse")
                .password("123456")
                .phoneNumber("07033490197")
                .accountCurrencyType("Canadian Dollars")
                .build();
        account = accountService.createAccount(registerUserRequest);
    }


    @Test
    void createAccount(){
        assertEquals("7033490197", account.getAccountNumber());
        assertEquals(CurrencyType.CAD, account.getAccountCurrencyType());
    }

    @Test
    void depositTest() throws CurrencyNotFound, AccountDoesNotExist {
        System.out.println(account);
        DepositRequest depositRequest = DepositRequest.builder()
                .accountNumber(account.getAccountNumber())
                .amount(BigDecimal.valueOf(5000))
                .currency(CurrencyType.valueOf("EUR")).build();
        System.out.println(accountService.deposit(depositRequest));

    }

}