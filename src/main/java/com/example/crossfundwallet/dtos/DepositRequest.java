package com.example.crossfundwallet.dtos;

import com.example.crossfundwallet.Data.Models.CurrencyType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DepositRequest {
    private CurrencyType currency;
    private BigDecimal amount = BigDecimal.ZERO;
    private String accountNumber;
}
