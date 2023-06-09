package com.organic.shop.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/api/u/contact")
public interface ContacController {
    @RequestMapping({"/", "", })
    ModelAndView goContact(@RequestParam(value = "use", required = false) String massage);

    @PostMapping("/send-message")
    ModelAndView sendMessage(@RequestParam(value = "name") String username,
                             @RequestParam(value = "email")String email,
                             @RequestParam(value = "message") String message);
}
