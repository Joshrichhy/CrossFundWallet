package com.example.crossfundwallet.dtos;

import com.example.crossfundwallet.Data.Models.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class TransactionReceipt {
    private String transactionId;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private BigDecimal transactionAmount;
    private String status;
}
