package com.example.crossfundwallet.Exceptions;

public class AccountDoesNotExist extends Throwable {
    public AccountDoesNotExist(String message) {
        super(message);
    }
}
