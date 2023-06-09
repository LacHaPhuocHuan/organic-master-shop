package com.organic.shop.controller;

import com.organic.shop.Dtos.UserDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/api/v/auth/register")
public interface RegisterController {

    @GetMapping(path = "/sign-up")
    public ModelAndView goSignUp();

    @PostMapping(path = "/sign-up")
    public ModelAndView goSignUp(@ModelAttribute("AccountRegister")UserDto accountRegister);

    @GetMapping(path ="/confirm/{token}")
    public ModelAndView goConfirmAccountByToken(@PathVariable String token);

}
