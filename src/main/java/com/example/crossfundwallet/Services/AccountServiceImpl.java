package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Repositories.AccountRepository;
import com.example.crossfundwallet.dtos.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(UserRequest userRequest) {
        Account account = new Account();
        account.setAccountFirstName(userRequest.getFirstName());
        account.setAccountLastName(userRequest.getLastName());
       account.setAccountNumber(generateAccountNumber(userRequest.getPhoneNumber()));
        return accountRepository.save(account);

    }

    private String generateAccountNumber(String phoneNumber) {
        return phoneNumber.substring(1, 11);
    }


}
