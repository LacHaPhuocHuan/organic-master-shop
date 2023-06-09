package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.TokenConfirmRepository;
import com.organic.shop.Daos.UserRepository;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.TokenConfirmAccount;
import com.organic.shop.entities.User;
import com.organic.shop.security.Role;
import com.organic.shop.service.RegisterService;
import com.organic.shop.utils.BadRequestException;
import com.organic.shop.utils.Email;
import com.organic.shop.utils.NotFoundException;
import com.organic.shop.utils.ServiceUnavailableException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    Email email;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    TokenConfirmRepository  confirmRepository;

    @Autowired
    UserRepository userRepository;
    private ModelMapper mapper=new ModelMapper();
    @Override
    public void SignUp(UserDto accountRegister)  {
        User user=mapper.map(accountRegister, User.class);
        User userFind=null;
        try{
           userFind= userRepository.findByEmail(user.getEmail())
                   .orElseThrow();
        }catch (Exception e){
//            e.printStackTrace();
        }

        if(Objects.isNull(userFind) || !userFind.isNonBlocked()){
            if(Objects.isNull(userFind)) {
                user.setCreateAt(new Date());
                user.setNonBlocked(false);
                user.setRole(Role.USER);

                user.setPassword(encoder.encode(accountRegister.getPassword()));
                userRepository.save(user);
            }

            TokenConfirmAccount tokenConfirmAccount=new
                    TokenConfirmAccount(
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(24),
                    user
            );
            confirmRepository.save(tokenConfirmAccount);

            String text= "Dear [User],\n" +
                    "\n" +
                    "Thank you for signing up with us. We are happy to confirm that your account has been successfully created.\n" +
                    "\n" +
                    "Please click on the link below to activate your account and begin using our services:\n" +
                    "\n \"http://localhost:8082/api/v/auth/register/confirm/"+ tokenConfirmAccount.getToken()+
                   "  \n" +
                    "\n" +
                    "If you have any questions or need assistance, please don't hesitate to contact us.\n" +
                    "\n" +
                    "Thank you,";
            try {
                email.sendSimpleMessage(user.getEmail(), "Confirm Account " + user.getFirstname() + " " + user.getLastname()
                        , text);
            }catch (Exception e){
                log.error("Exception : {}", e.getMessage());
            }
            return;
        }else {
            throw new BadRequestException("Email existed!");
        }

    }

    @Override
    public void confirmAccountByToken(String token) {
        TokenConfirmAccount confirmAccount=null;
        confirmAccount= confirmRepository.findByToken(token)
                .orElseThrow(()->new NotFoundException("Token haven't usefully or Expired"));

        if(!Objects.isNull(confirmAccount)){
            try {
                User user=confirmRepository.findUserById(confirmAccount.getId()).orElseThrow();
                //   .orElseThrow(()->new ServiceUnavailableException("User not found!"));
                user.setNonBlocked(true);
                confirmAccount.setConfirmAt(LocalDateTime.now());
                userRepository.save(user);
                confirmRepository.save(confirmAccount);
                log.info("Confirm Success!");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


}
