package com.organic.shop.utils;

public class PasswordOrUsernameFailException extends RuntimeException{
    public PasswordOrUsernameFailException(String message) {
        super(message);
    }
}
