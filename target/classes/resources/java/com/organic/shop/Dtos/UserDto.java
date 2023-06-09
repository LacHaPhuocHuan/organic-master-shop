package com.organic.shop.Dtos;

import com.organic.shop.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private Role role;
    private String avatar;
    private String address;
    private boolean isNonBlocked;
    private Date CreateAt;
    private Date dateofBirth;

    public boolean isNonBlocked() {
        return isNonBlocked;
    }
}
