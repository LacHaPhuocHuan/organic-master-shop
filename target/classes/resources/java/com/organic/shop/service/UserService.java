package com.organic.shop.service;

import com.organic.shop.Dtos.UserChangeDto;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getUserAll();

    void blockedUserAccount(Long id);

    void changeAccount(UserChangeDto userDto, Long id);

    void changeAvatar(String nameAva, String email);

    User findUserById(Long id);
}
