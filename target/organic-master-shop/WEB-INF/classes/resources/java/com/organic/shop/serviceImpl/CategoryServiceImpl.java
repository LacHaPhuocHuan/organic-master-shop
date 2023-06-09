package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.CategoryRepository;
import com.organic.shop.Dtos.CategoryDto;
import com.organic.shop.entities.Category;
import com.organic.shop.service.CategoryService;
import com.organic.shop.utils.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private ModelMapper mapper=new ModelMapper();
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryDto> getCategories() {

        List<Category> categories=categoryRepository.findAll();
        categories.stream()
                .forEach(t-> System.out.println(t.getName()));
        List<CategoryDto> dtos=categories.stream()
                        .map(t->mapper.map(t,CategoryDto.class))
                                .collect(Collectors.toList());
        dtos.stream()
                .forEach(t-> System.out.println(t.getId()));

        return dtos;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(mapper.map(categoryDto,Category.class));
    }

    @Override
    public CategoryDto findCategoryById(Long idProduct) {
        Category category=categoryRepository.findById(idProduct)
                .orElseThrow(()->new NotFoundException(""));
        return mapper.map(category, CategoryDto.class);
    }
}
