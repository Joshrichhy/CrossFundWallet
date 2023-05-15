package com.example.crossfundwallet.Data.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Account {
    @Id
    private String accountId;
    private String accountCurrencyType;
    private String accountBalance;
    private String accountNumber;
    private String accountStatus;
    private String transactionHistory;
}
