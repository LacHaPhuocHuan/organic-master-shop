package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.CategoryBlogRepository;
import com.organic.shop.Dtos.CategoryBlogDto;
import com.organic.shop.entities.CategoryBlog;
import com.organic.shop.service.CategoryBlogService;
import com.organic.shop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryBlogServiceImpl implements CategoryBlogService{
    private ModelMapper mapper=new ModelMapper();
    @Autowired
    CategoryBlogRepository categoryBlogRepository;
    @Override
    public Set<CategoryBlogDto> getALLCategoryBlog() {
        List<CategoryBlog> categoryBlogs=categoryBlogRepository.findAll();
        return categoryBlogs.stream()
                .map(t->mapper.map(t, CategoryBlogDto.class)).collect(Collectors.toSet());
    }
}
