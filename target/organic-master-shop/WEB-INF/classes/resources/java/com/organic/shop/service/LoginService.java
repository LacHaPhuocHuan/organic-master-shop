package com.organic.shop.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    void signIn(String username, String password);
}
