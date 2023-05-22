package com.example.crossfundwallet.dtos.request;

import com.example.crossfundwallet.Data.Models.CurrencyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class WithdrawalRequest {
    private String transactionType;
    private CurrencyType currencyType;
    private BigDecimal amount;
    private String accountNumber;
    private String accountPin;
}
