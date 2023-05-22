package com.example.crossfundwallet.Data.Models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
public class Account {
    @Id
    private String accountId;
    private String accountFirstName;
    private String accountLastName;
    private BigDecimal accountBalance;
    private String accountNumber;
    private String accountCurrencyType;
    private String accountStatus;
    private String transactionHistory;

}
