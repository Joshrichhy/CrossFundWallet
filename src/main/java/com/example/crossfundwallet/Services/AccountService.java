package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.dtos.UserRequest;

public interface AccountService {
    public Account createAccount(UserRequest userRequest);
}
