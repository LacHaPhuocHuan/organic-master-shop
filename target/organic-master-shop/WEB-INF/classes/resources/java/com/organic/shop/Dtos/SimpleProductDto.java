package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleProductDto {
    private final static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("dd/MM/yyyy");
    private Long id;
    private String name;
    private Integer inStock;
    private Integer unitSold;
    private String ExpiredDate;


}
