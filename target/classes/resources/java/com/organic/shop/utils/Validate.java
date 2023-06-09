package com.organic.shop.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validate {
    private final static String PHONE_VALID_REGEX="^\\d{10}$";
    public static Boolean validatePhone(String phone){
        Pattern pattern = Pattern.compile(PHONE_VALID_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
