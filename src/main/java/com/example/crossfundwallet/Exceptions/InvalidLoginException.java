package com.example.crossfundwallet.Exceptions;

public class InvalidLoginException extends Throwable{
    public InvalidLoginException(String message){
        super(message);
    }
}
