package com.organic.shop.service;

import com.organic.shop.Dtos.BlogDto;
import com.organic.shop.Dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    List<BlogDto> getAllBlog();

    List<BlogDto> getNewBlogs();

    BlogDto findBlogById(Long id);

    List<BlogDto> getBlogByKeyAndCategory(String keyWord, Long idBlog);

    List<BlogDto> getBlogByKey(String keyWord);

    UserDto getAuthor(Long id);
}
