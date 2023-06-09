package com.organic.shop.restImpl;

import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.rest.HomeUserRest;
import com.organic.shop.security.MyUserService;
import com.organic.shop.service.BlogService;
import com.organic.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HomeRestImpl extends  BaseUserRest implements HomeUserRest {
    @Autowired
    ProductService productService;
    @Autowired
    BlogService blogService;

    @Autowired
    MyUserService userService;
    @Override
    public ModelAndView goHome() {
        _ModelAndView.setViewName("/user/index");
        _ModelAndView.addObject("featuredCategory", categoryDtos.stream()
                .limit(4).collect(Collectors.toList()));
        _ModelAndView.addObject("featuredProduct",
                productService.getFeaturedProduct().stream().limit(8).collect(Collectors.toList()));
        List<ProductDto> productDtos=productService.getAll();
        _ModelAndView.addObject("latestProducts",productService.getLatestProducts().stream().limit(6).collect(Collectors.toList()));
//        _ModelAndView.addObject("latestProducts",productService.getLatestProducts().stream().limit(6).
//                collect(Collectors.toList()));
//        _ModelAndView.addObject("reviewProducts",productService.getReviewProducts(productDtos).stream().limit(6).collect(Collectors.toList()));
        _ModelAndView.addObject("topRatedProducts",productService.getTopRatedProducts().stream().limit(6).collect(Collectors.toList()));


        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        _ModelAndView.addObject("blogs", blogService.getAllBlog().stream().limit(3).collect(Collectors.toList()));
        return _ModelAndView;
    }

//    @Override
//    public ModelAndView find(String keyWord) {
//        _ModelAndView.setViewName("/user/search-gird");
//        List<ProductDto> productDtos=productService.getProductByKey(keyWord);
//        List<BlogDto> blogDtos=blogService.getBlogByKey(keyWord);
//        return _ModelAndView;
//    }

}
