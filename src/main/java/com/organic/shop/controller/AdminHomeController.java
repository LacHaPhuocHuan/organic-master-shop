package com.organic.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/admin")
public interface AdminHomeController {
    @RequestMapping({"/home","/", ""})
    ModelAndView goHome();


}
