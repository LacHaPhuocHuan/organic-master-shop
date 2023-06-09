package com.organic.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/api/u/home")
public interface HomeUserController {

    @RequestMapping({"/",""})
    ModelAndView goHome();






}
