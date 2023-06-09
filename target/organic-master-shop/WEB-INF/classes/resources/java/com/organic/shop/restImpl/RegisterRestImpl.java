package com.organic.shop.restImpl;

import com.organic.shop.Dtos.UserDto;
import com.organic.shop.rest.RegisterRest;
import com.organic.shop.service.RegisterService;
import com.organic.shop.utils.BadRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Log4j2
public class RegisterRestImpl extends BaseUserRest implements RegisterRest {
    @Autowired
    RegisterService registerService;
    @Override
    public ModelAndView goSignUp() {
        _ModelAndView.setViewName("/user/register");
        _ModelAndView.addObject("AccountRegister", new UserDto());
//        log.error("URL : {} "+ _ModelAndView.getViewName());
        return _ModelAndView;
    }

    @Override
    public ModelAndView goSignUp(UserDto accountRegister) throws BadRequestException {
       registerService.SignUp(accountRegister);
       _ModelAndView.setViewName("/user/login");
       return _ModelAndView;
    }

    @Override
    public ModelAndView goConfirmAccountByToken(String token) {
        log.error("token confirm : {}", token);
        registerService.confirmAccountByToken(token);
        _ModelAndView.setView(new RedirectView("/api/v/auth/login"));
        return _ModelAndView;
    }
}
