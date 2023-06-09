package com.organic.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class Email {
    @Autowired
    JavaMailSender mailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("phbinhthuongtam@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
//            mailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    public void sentMailWithAttachment(String to, String subject, String text,String pathToAttachment ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);
        mailSender.send(message);
    }





}
