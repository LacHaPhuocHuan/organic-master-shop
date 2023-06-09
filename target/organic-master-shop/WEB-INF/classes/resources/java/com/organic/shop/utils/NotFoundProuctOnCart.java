package com.organic.shop.utils;

public class NotFoundProuctOnCart extends RuntimeException{
    public NotFoundProuctOnCart(String message) {
        super(message);
    }
}
