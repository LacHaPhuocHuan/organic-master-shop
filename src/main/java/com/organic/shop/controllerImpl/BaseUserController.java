package com.organic.shop.controllerImpl;

import com.organic.shop.Dtos.CategoryBlogDto;
import com.organic.shop.Dtos.CategoryDto;
import com.organic.shop.Dtos.MenuDto;
import com.organic.shop.security.MyUserService;
import com.organic.shop.service.CategoryBlogService;
import com.organic.shop.service.CategoryService;
import com.organic.shop.service.MenuService;
import com.organic.shop.utils.MenuAccordingRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//@RestController
public class BaseUserController {
    @Autowired
    MenuService service;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryBlogService categoryBlogService;
    protected ModelAndView _ModelAndView=new ModelAndView();
    @Autowired
    MyUserService myUserService;
    protected List<CategoryDto> categoryDtos;
    @PostConstruct
    public void initModel(){
        categoryDtos=categoryService.getCategories();
        System.out.println("Category: "+ categoryDtos.size());
        List<MenuDto> menuDtos= service.getMenu(MenuAccordingRole.USER);
        System.out.println("Menu "+menuDtos.size());
        _ModelAndView.addObject("menu",menuDtos);
        _ModelAndView.addObject("submenu",service.getSubMenu());
        _ModelAndView.addObject("category", categoryDtos);
//        _ModelAndView=new ModelAndView();
        Set<CategoryBlogDto> categoryBlogDtos=categoryBlogService.getALLCategoryBlog();
        _ModelAndView.addObject("categoryBlogDtos",categoryBlogDtos);

        if(!Objects.isNull(myUserService.getUser()))
            System.out.println("USER CURRENT : "+myUserService.getUser().getRole().name());

    }

}
