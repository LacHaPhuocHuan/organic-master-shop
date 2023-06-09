package com.organic.shop.serviceImpl;

import com.organic.shop.service.LoginService;
import com.organic.shop.utils.PasswordOrUsernameFailException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Log4j2
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void signIn(String username, String password) {
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               username, password
        ));

        if(authenticate.isAuthenticated()){
            log.info("OK");
            System.out.println("OK");
        }else{
            System.out.println("Thong Tin Sai");
            throw new
                    PasswordOrUsernameFailException("Thong tin dang nhap sai roi");
        }
    }
}
