package com.organic.shop.utils;

public class InternalServerErrorException extends RuntimeException{
    public InternalServerErrorException(String message) {
        super(message);
    }
}
