package com.organic.shop.service;

import com.organic.shop.Dtos.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDto> getCategories();

    void addCategory(CategoryDto categoryDto);

    CategoryDto findCategoryById(Long idProduct);
}
