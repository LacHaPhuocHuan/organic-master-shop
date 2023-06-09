package com.organic.shop.service;

import com.organic.shop.Dtos.CategoryBlogDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CategoryBlogService {

    Set<CategoryBlogDto> getALLCategoryBlog();
}
