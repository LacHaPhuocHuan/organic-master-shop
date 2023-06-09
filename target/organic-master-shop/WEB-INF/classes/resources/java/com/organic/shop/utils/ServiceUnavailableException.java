package com.organic.shop.utils;

public class ServiceUnavailableException extends RuntimeException{
    public ServiceUnavailableException() {
    }
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
