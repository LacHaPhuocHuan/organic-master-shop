package com.organic.shop.controller;

import com.organic.shop.Dtos.CategoryDto;
import com.organic.shop.Dtos.ProductDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RequestMapping(path = "/admin/product")
public interface AdminProductController {
    @GetMapping({"/", ""})
    ModelAndView goProduct();
    @RequestMapping("/page-add-product")
    ModelAndView goAddProduct(HttpServletRequest request);
//    @RequestMapping("/add")
//    ModelAndView addProduct(HttpServletRequest request, @ModelAttribute(name ="newProduct") ProductDto productDto,
//                            @RequestParam(name = "file")MultipartFile file);
    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    ModelAndView addProduct(HttpServletRequest request,@RequestParam(name = "name")String name ,
                                @RequestParam(name = "date") String date,
                                @RequestParam(name ="idCategory") Long id,
                                @RequestParam(name = "quantity") Integer quantity,
                                @RequestParam(name="price") String price,
                                @RequestParam(name = "file")MultipartFile file) throws ParseException;
    @RequestMapping("/add-p")
    ModelAndView addP(HttpServletRequest request,
                            @RequestParam(name = "file")MultipartFile file);
    @PostMapping("/add-product-img/{id}")
    ModelAndView uploadImg( @RequestParam("file")MultipartFile img,@PathVariable(name = "id") Long id, HttpServletRequest request);
    @RequestMapping("/delete-product/{id}")
    ModelAndView deleteProduct(@PathVariable(name = "id") Long id);
    @RequestMapping("/delete-products")
    ModelAndView deleteProduct(@RequestParam("selectedId") List<Long> ids);
    @RequestMapping("/page-add-category")
    ModelAndView addCategory();

    @PostMapping("/add-category")
    ModelAndView addCategory(HttpServletRequest request, @ModelAttribute CategoryDto categoryDto,
                             @RequestParam("file")MultipartFile img );
    @RequestMapping("/page-edit-product")
    ModelAndView editProducta();
    @RequestMapping("/delete-category/{id}")
    ModelAndView deleteCategory(@PathVariable(name = "id") Long id);

    @RequestMapping("/edit-product/{id}")
    ModelAndView editProduct(@ModelAttribute ProductDto productDto, @PathVariable Long id,
                             @RequestParam("file")MultipartFile file, HttpServletRequest request);





}
