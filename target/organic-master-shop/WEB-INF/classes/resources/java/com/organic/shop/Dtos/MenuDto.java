package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
    private Long id;
    private String name;
    private String link;


}
