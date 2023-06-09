package com.organic.shop.rest;

import com.itextpdf.text.pdf.qrcode.Mode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(path = "/api/u")

public interface BlogRest {
    @RequestMapping("/blog")
    ModelAndView goBlog();

    @GetMapping("/blog/blog-details/{id}")
    ModelAndView goBlogDetails(@PathVariable(name = "id") Long id );

}
