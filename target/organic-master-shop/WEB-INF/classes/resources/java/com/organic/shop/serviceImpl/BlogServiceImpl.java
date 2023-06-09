package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.BlogRepository;
import com.organic.shop.Dtos.BlogDto;
import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.Blog;
import com.organic.shop.entities.User;
import com.organic.shop.service.BlogService;
import com.organic.shop.utils.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private ModelMapper mapper=new ModelMapper();
    @Autowired
    BlogRepository blogRepository;

    private  List<Blog> blogs=new ArrayList<>();
    @Override
    public List<BlogDto> getAllBlog() {
        blogs=blogRepository.findAll();
        return blogs.stream().map(t->mapper.map(t, BlogDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> getNewBlogs() {
        Collections.sort(blogs, new Comparator<Blog>() {
            @Override
            public int compare(Blog o1, Blog o2) {
                return o2.getCreateAt().compareTo(o1.getCreateAt());
            }
        });
        return blogs.stream().map(t->mapper.map(t, BlogDto.class)).collect(Collectors.toList());
    }

    @Override
    public BlogDto findBlogById(Long id) {
        Blog blog=blogRepository.findById(id).orElseThrow(()->new NotFoundException("Not found blog which have id that is :" + id));

        return mapper.map(blog, BlogDto.class);
    }

    @Override
    public List<BlogDto> getBlogByKeyAndCategory(String keyWord, Long idBlog) {
        List<Blog> blogs=blogRepository.findByKeyAndCategory(keyWord,idBlog);
        return blogs.stream()
                .map(t->mapper.map(t, BlogDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BlogDto> getBlogByKey(String keyWord) {
        try {
            List<Blog> blogs=blogRepository.findByKey(keyWord);
        }catch (Exception e){
            e.printStackTrace();
        }

        return blogs.stream()
                .map(t->mapper.map(t, BlogDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getAuthor(Long id) {
        User user
                =blogRepository.findAuthor(id);
        return mapper.map(user,UserDto.class);
    }
}
