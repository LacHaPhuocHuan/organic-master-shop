package com.organic.shop.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/admin")
public interface AdminHomeRest {
    @RequestMapping({"/home","/", ""})
    ModelAndView goHome();


}
