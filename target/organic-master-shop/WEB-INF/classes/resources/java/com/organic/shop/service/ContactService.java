package com.organic.shop.service;

import org.springframework.stereotype.Service;

@Service
public interface ContactService {

    void resultMessageAndSendMailResponse(String username, String email, String message) throws Exception;
}
