package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtoMultipart {
    private Long id;
    private String name;
    private CommonsMultipartFile img;
    private Long idCategory;
    private Date expireDate;
    private Integer untilInStock;
}
