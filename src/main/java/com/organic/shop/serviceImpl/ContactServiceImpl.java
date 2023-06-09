package com.organic.shop.serviceImpl;

import com.organic.shop.service.ContactService;
import com.organic.shop.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    Email emailService;
    @Override
    public void resultMessageAndSendMailResponse(String username, String email, String message) throws Exception {
        emailService.sendSimpleMessage(email,"thank you", message);
    }
}
