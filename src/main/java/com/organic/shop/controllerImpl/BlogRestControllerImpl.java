package com.organic.shop.controllerImpl;

import com.organic.shop.Dtos.BlogDto;
import com.organic.shop.controller.BlogController;
import com.organic.shop.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BlogRestControllerImpl extends BaseUserController implements BlogController {
    @Autowired
    BlogService blogService;
    @Override
    public ModelAndView goBlog() {
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        _ModelAndView.setViewName("/user/blog");
        List<BlogDto> blogDtos=blogService.getAllBlog();
        List<BlogDto> newBlogDtos=blogService.getNewBlogs();
        _ModelAndView.addObject("blogDtos", blogDtos);
        _ModelAndView.addObject("newBlogDtos", newBlogDtos.stream().limit(3).collect(Collectors.toList()));
        return _ModelAndView;
    }

    @Override
    public ModelAndView goBlogDetails(Long id) {
        _ModelAndView.setViewName("/user/blog-details");
        BlogDto blogDto=blogService.findBlogById(id);
        _ModelAndView.addObject("blogDetails", blogDto);
        _ModelAndView.addObject("user",blogService.getAuthor(id));
        _ModelAndView.addObject("blogs", blogService.getAllBlog().stream().limit(3).collect(Collectors.toList()));
        return _ModelAndView;
    }
}
