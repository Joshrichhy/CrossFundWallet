package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Exceptions.AccountDoesNotExist;
import com.example.crossfundwallet.Exceptions.CurrencyNotFound;
import com.example.crossfundwallet.Exceptions.InsufficientFundException;
import com.example.crossfundwallet.Exceptions.InvalidCredentialException;
import com.example.crossfundwallet.dtos.DepositRequest;
import com.example.crossfundwallet.dtos.RegisterUserRequest;
import com.example.crossfundwallet.dtos.TransactionReceipt;
import com.example.crossfundwallet.dtos.WithdrawalRequest;


public interface AccountService {
    Account createAccount(RegisterUserRequest userRequest);
    TransactionReceipt deposit(DepositRequest depositRequest) throws AccountDoesNotExist, CurrencyNotFound;

    void deleteAllAccounts();

    Account findByAccountNumber(String accountNumber);

    TransactionReceipt withdraw(WithdrawalRequest withdrawalRequest) throws InvalidCredentialException, InsufficientFundException, CurrencyNotFound;

}
