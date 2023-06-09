package com.organic.shop.Dtos;

import com.organic.shop.entities.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDto {
    private String username;
    private String country;
    private String address;
    private String townOrCity;
    private Long phone;
    private String email;
    private  String orderNote;

    private Long paymentMethod_Id;
}
