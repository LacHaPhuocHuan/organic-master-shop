package com.organic.shop.utils;

public class BadRequestException extends  RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
