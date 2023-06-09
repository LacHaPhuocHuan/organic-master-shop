package com.organic.shop.Dtos;

import com.organic.shop.entities.Category;
import com.organic.shop.utils.Shipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String img;
    private Long idCategory;
    private Date expireDate;
    private Integer untilInStock;
    private Date addAt;
    private String makeIn;
    private Double price;
    private Double weigh;
    private Shipping shipping;




}
