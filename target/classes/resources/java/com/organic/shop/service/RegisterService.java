package com.organic.shop.service;

import com.organic.shop.Dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {

    void SignUp(UserDto accountRegister) ;

    void confirmAccountByToken(String token);
}
