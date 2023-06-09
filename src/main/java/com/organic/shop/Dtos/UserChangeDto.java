package com.organic.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangeDto {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private String rePassword;
}
