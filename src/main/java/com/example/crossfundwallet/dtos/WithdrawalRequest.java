package com.example.crossfundwallet.dtos;

import com.example.crossfundwallet.Data.Models.Account;
import com.example.crossfundwallet.Data.Models.CurrencyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawalRequest {
    private CurrencyType currencyType;
    private BigDecimal amount;
    private String accountNumber;
    private String accountPin;
}
