package com.organic.shop.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/api/u/contact")
public interface ContactRest {
    @RequestMapping({"/", "", })
    ModelAndView goContact(@RequestParam(value = "use", required = false) String massage);

    @PostMapping("/send-message")
    ModelAndView sendMessage(@RequestParam(value = "name") String username,
                             @RequestParam(value = "email")String email,
                             @RequestParam(value = "message") String message);
}
