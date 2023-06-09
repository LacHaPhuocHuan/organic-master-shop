package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementOnCart {
    private Integer quantity;
    private Double totalPrice;
    private ProductDto productDto;

    public Double getSubTotalPrice() {
        return quantity*productDto.getPrice();
    }
}
