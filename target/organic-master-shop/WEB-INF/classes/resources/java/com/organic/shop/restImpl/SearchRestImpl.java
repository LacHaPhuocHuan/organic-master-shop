package com.organic.shop.restImpl;

import com.organic.shop.Dtos.BlogDto;
import com.organic.shop.Dtos.PaginatesDto;
import com.organic.shop.Dtos.ProductDto;
import com.organic.shop.rest.SearchRest;
import com.organic.shop.service.BlogService;
import com.organic.shop.service.CategoryService;
import com.organic.shop.service.IPaginatesService;
import com.organic.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class SearchRestImpl extends  BaseUserRest implements SearchRest {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BlogService blogService;
    @Autowired
    IPaginatesService paginatesService;
    List<ProductDto> productDtos = new ArrayList<>();
    List<BlogDto> blogDtos = new ArrayList<>();

    @Override
    public ModelAndView find(String keyWord, Long idProduct) {
        _ModelAndView.setViewName("/user/search-grid");
        _ModelAndView.addObject("userCurrent", myUserService.getUser());
        if (idProduct != 0 && !Objects.isNull(idProduct)) {
            productDtos = productService.getProductByKeyAndCategory(keyWord, idProduct);
//            _ModelAndView.addObject("ctf", categoryService.findCategoryById(idProduct));
        } else {
            productDtos= productService.getProductByKey(keyWord);
//            _ModelAndView.addObject("ctf", 0);
        }
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(productDtos.size(),
                8,
                1);

        blogDtos=blogService.getBlogByKey(keyWord);
        _ModelAndView.addObject("sizeFound", productDtos.size());
        _ModelAndView.addObject("paginatesDto",paginatesDto);
        _ModelAndView.addObject("productsFound",productDtos.stream().limit(9).collect(Collectors.toList()));
        _ModelAndView.addObject("blogsFound",blogDtos.stream().limit(3).collect(Collectors.toList()));
        return _ModelAndView;
    }
    @Override
    public ModelAndView find(Integer currentPage) {
        List<ProductDto> products = new ArrayList<>();
        List<BlogDto> blogs = new ArrayList<>();
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(productDtos.size(),
                9,
                currentPage);
        for (int i=paginatesDto.getStart()-1; i<paginatesDto.getEnd(); i++
        ) {
            products.add(productDtos.get(i));
        }
        _ModelAndView.addObject("paginatesDto",paginatesDto);
        _ModelAndView.addObject("sizeFound", productDtos.size());

        _ModelAndView.addObject("productsFound",products);
        _ModelAndView.addObject("blogsFound",blogDtos.stream().limit(3).collect(Collectors.toList()));
        return _ModelAndView;
    }

    @Override
    public ModelAndView findBlog(Integer currentPage) {
        List<ProductDto> products = new ArrayList<>();
        List<BlogDto> blogs = new ArrayList<>();
        PaginatesDto paginatesDto=paginatesService.getInfoPaginatesDto(blogDtos.size(),
                3,
                currentPage);
        for (int i=paginatesDto.getStart()-1; i<paginatesDto.getEnd(); i++
        ) {
            blogs.add(blogDtos.get(i));
        }
        _ModelAndView.addObject("productsFound",products.stream().limit(9).collect(Collectors.toList()));
        _ModelAndView.addObject("blogsFound",blogs);
        return _ModelAndView;
    }

    @Override
    public ModelAndView findAndFilter(List<ProductDto> productDtos, List<BlogDto> blogDtos, Long idProduct, Long idBlog) {
        return null;
    }
}
