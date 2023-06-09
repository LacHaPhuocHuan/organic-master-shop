package com.organic.shop.controller;

import com.organic.shop.Dtos.ElementOnCart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/v/u/cart")
public interface CartController {
    @GetMapping({"/", ""})
    public ModelAndView goCart(HttpServletRequest request, HttpSession session);
    @RequestMapping("/add-product/{id}")
    ModelAndView addProductIntoCart(HttpServletRequest request, HttpSession session,
                                    @PathVariable(name = "id") Long id);
    @RequestMapping("/add-var-product/{id}")
    ModelAndView addOneProductIntoCart(HttpServletRequest request, HttpSession session,
                                    @PathVariable(name = "id") Long id);
    @RequestMapping("/update-cart")
    ModelAndView updateCart(HttpServletRequest request, HttpSession session,@ModelAttribute(name = "carts")HashMap<Integer, ElementOnCart> carts);
    @RequestMapping("/delete/{id}")
    ModelAndView deleteOneElement(HttpServletRequest request, HttpSession session, @ModelAttribute(name = "id") Integer id);
    @RequestMapping("/delete")
    ModelAndView deleteVariousElement(HttpServletRequest request, HttpSession session,@RequestParam("id-element")List<Long> ids);
    @PostMapping("/update/{id}")
    ModelAndView updateById(HttpServletRequest request, HttpSession session,@RequestParam("quantity")Integer quantity,
                            @PathVariable(name = "id") Integer id);

    @PostMapping("/discount-code")
    ModelAndView discount(HttpServletRequest request, HttpSession session,@RequestParam("code")String code);





}
