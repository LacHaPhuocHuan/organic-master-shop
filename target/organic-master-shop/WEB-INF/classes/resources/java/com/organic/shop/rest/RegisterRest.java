package com.organic.shop.rest;

import com.organic.shop.Dtos.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(path = "/api/v/auth/register")
public interface RegisterRest {

    @GetMapping(path = "/sign-up")
    public ModelAndView goSignUp();

    @PostMapping(path = "/sign-up")
    public ModelAndView goSignUp(@ModelAttribute("AccountRegister")UserDto accountRegister);

    @GetMapping(path ="/confirm/{token}")
    public ModelAndView goConfirmAccountByToken(@PathVariable String token);

}
