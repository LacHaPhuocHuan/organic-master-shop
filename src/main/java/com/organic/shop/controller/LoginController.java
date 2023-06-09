package com.organic.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/api/v/auth/login")
public interface LoginController {
    @RequestMapping( {"/", ""})
    public ModelAndView goLogin();

    //Cái này thường dùng để trả lại jwt token, nhưng không dùng thì không cần.
    @PostMapping("/sign-in")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password);
    @RequestMapping({"/home"})
    public ModelAndView goHome();

    @RequestMapping("/logout")
    public ModelAndView logout();
}
