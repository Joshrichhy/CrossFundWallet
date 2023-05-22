package com.example.crossfundwallet.dtos.request;

import com.example.crossfundwallet.Data.Models.CurrencyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositRequest {
    private String transactionType;
    private CurrencyType currency;
    private BigDecimal amount = BigDecimal.ZERO;
    private String accountNumber;
}
