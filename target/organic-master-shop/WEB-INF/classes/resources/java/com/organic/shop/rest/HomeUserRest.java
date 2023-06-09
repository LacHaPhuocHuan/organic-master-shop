package com.organic.shop.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/api/u/home")
public interface HomeUserRest {

    @RequestMapping({"/",""})
    ModelAndView goHome();






}
