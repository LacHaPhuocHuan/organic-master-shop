package com.organic.shop.rest;


import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping(path = "/api/u/shop")
public interface ShopRest {
    @RequestMapping({"/",""})
    ModelAndView goShop();

    @GetMapping("/product-details/{id}")
    ModelAndView goDetailsProduct(@PathVariable(name = "id") Long id);

    @GetMapping("/product-cartegory/{id}")
    ModelAndView getProductCategory(@PathVariable(name = "id") Long id);

    @GetMapping("/all/{currentPage}")
    ModelAndView getPageAll(@PathVariable(name ="currentPage" ) Integer currentPage);
    @GetMapping("/sale/{currentPage}")
    ModelAndView getPageSale(@PathVariable(name ="currentPage" ) Integer currentPage);

    @GetMapping("/sort")
    ModelAndView sortBy(@RequestParam(name = "sortBy") Integer sortBy);


}
