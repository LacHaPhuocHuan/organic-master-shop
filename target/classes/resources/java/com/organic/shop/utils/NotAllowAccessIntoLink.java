package com.organic.shop.utils;

public class NotAllowAccessIntoLink extends RuntimeException{
    public NotAllowAccessIntoLink(String message) {
        super(message);
    }
}
