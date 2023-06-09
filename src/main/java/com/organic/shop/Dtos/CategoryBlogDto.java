package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBlogDto {
    private Long id;
    private String name;
    private String description;
    private String img;
}
