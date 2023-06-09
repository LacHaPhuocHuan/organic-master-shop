package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.UserRepository;
import com.organic.shop.Dtos.UserChangeDto;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.User;
import com.organic.shop.security.Role;
import com.organic.shop.service.UserService;
import com.organic.shop.utils.NotFoundException;
import com.organic.shop.utils.Validate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private ModelMapper mapper=new ModelMapper();
    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserDto> getUserAll() {
        List<User> users=userRepository.findAll();
        return users.stream()
                .map(u-> mapper.map(u, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void blockedUserAccount(Long id) {
        User user=userRepository.findById(id).orElseThrow(()->new NotFoundException("Not found account which needed blocking!"));
        if(!user.isNonBlocked())
            user.setNonBlocked(false);
    }

    @Override
    public void changeAccount(UserChangeDto userDto, Long id) {
        User user=userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Not found account that needed change"));
         if(!Objects.isNull(userDto.getLastname())
         && !Objects.isNull(userDto.getFirstname())){
             user.setFirstname(userDto.getFirstname());
             user.setLastname((userDto.getLastname()));
         }
         if(!Objects.isNull(userDto.getPhone()) && Validate.validatePhone(userDto.getPhone()))
             user.setPhone(userDto.getPhone());
         if(!Objects.isNull(userDto.getEmail()) || userDto.getEmail()=="")
             user.setEmail(userDto.getEmail());

        userRepository.save(user);
    }

    @Override
    public void changeAvatar(String nameAva, String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException(""));
        if (!Objects.isNull(user)){
            user.setAvatar(nameAva);
            userRepository.save(user);
        }
    }

    @Override
    public User findUserById(Long id) {

        return userRepository.findById(id).orElseThrow(()->new NotFoundException(""));
    }
}
