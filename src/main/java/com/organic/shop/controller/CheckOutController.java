package com.organic.shop.controller;

import com.organic.shop.Dtos.BillingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/api/v/u/checkout")
public interface CheckOutController {
    @GetMapping({"","/"})
    ModelAndView goCheckOut(HttpSession session);
    @PostMapping({"/order"})
    ModelAndView order(@ModelAttribute BillingDto  billingDto,HttpSession session , HttpServletRequest request) throws Exception;

}
