package com.example.crossfundwallet.Exceptions;

public class InsufficientFundException extends Throwable {
    public InsufficientFundException(String message) {
        super(message);
    }
}
