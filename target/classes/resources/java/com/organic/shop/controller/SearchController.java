package com.organic.shop.controller;

import com.organic.shop.Dtos.BlogDto;
import com.organic.shop.Dtos.ProductDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/api/u/search/")
public interface SearchController {
    @GetMapping({"/", ""})
    ModelAndView find(@RequestParam(name = "keyWord") String keyWord,
                    @RequestParam(name = "category") Long category

                     );
    @GetMapping({"/product/{currentPage}"})
    ModelAndView find( @PathVariable(name = "currentPage", required = false) Integer currentPage
    );
    @GetMapping({"/blog/{currentPage}"})
    ModelAndView findBlog( @PathVariable(name = "currentPage", required = false) Integer currentPage
    );
    @GetMapping({"/filter"})
    ModelAndView findAndFilter(@ModelAttribute(name = "productsFound") List<ProductDto> productDtos,
                               @ModelAttribute(name = "blogsFound") List<BlogDto> blogDtos,
                               @RequestParam(name = "department") Long idProduct,
                               @RequestParam(name = "category")Long idBlog );
}
