package com.example.crossfundwallet.utils;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.dtos.request.RegisterUserRequest;

public class Mapper {
    public static void mapAccount(RegisterUserRequest userRequest, Account account) {

        account.setAccountFirstName(userRequest.getFirstName());
        account.setAccountLastName(userRequest.getLastName());
        account.setAccountPin(userRequest.getAccountPin());
        account.setAccountStatus("ACTIVE");
    }
}
