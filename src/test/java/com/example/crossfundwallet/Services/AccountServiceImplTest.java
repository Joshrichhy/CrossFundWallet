package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.Exceptions.InsufficientFundException;
import com.example.crossfundwallet.Exceptions.InvalidCredentialException;
import com.example.crossfundwallet.dtos.request.DepositRequest;
import com.example.crossfundwallet.dtos.request.RegisterUserRequest;
import com.example.crossfundwallet.dtos.request.WithdrawalRequest;
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
    void setUp() throws CurrencyNotFound, AccountDoesNotExist {
        accountService.deleteAllAccounts();
        registerUserRequest = RegisterUserRequest.builder().DOB("12/17/1994")
                .emailAddress("kusejoshua@gmail.com")
                .firstName("Joshua")
                .lastName("Oluwakuse")
                .password("123456")
                .accountPin("1234")
                .phoneNumber("07033490197")
                .accountCurrencyType("Canadian Dollars")
                .build();
        account = accountService.createAccount(registerUserRequest);

        DepositRequest depositRequest = DepositRequest.builder()
                .accountNumber(account.getAccountNumber())
                .amount(BigDecimal.valueOf(5000))
                .currency(CurrencyType.valueOf("GBP")).build();
        accountService.deposit(depositRequest);
    }


    @Test
    void createAccount(){
        assertNotNull(account);
        assertEquals(CurrencyType.CAD, account.getAccountCurrencyType());
    }

    @Test
    void depositTest() {
        assertEquals(BigDecimal.valueOf(8350.00), accountService.findByAccountNumber(account.getAccountNumber()).getAccountBalance());
    }
    @Test
            void WithdrawalTest() throws InvalidCredentialException, InsufficientFundException, CurrencyNotFound {
        WithdrawalRequest withdrawalRequest = WithdrawalRequest.builder()
                .accountPin(registerUserRequest.getAccountPin())
                .accountNumber(account.getAccountNumber())
                .amount(BigDecimal.valueOf(3000))
                .accountPin("1234").currencyType(CurrencyType.NGN).build();
        System.out.println(accountService.withdraw(withdrawalRequest));


        assertEquals(BigDecimal.valueOf(5400.00), accountService.findByAccountNumber(account.getAccountNumber()).getAccountBalance());

    }


}