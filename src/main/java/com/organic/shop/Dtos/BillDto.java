package com.organic.shop.Dtos;

import com.organic.shop.entities.User;
import com.organic.shop.utils.StatusDelivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    private final static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    private Long id;
    private String SaleOfDate;
    private String username;
    private Double totalPrice;
    private String status;
    private String location;
    private String phone;

    public BillDto(Long id, Date saleOfDate, String username, Double totalPrice, StatusDelivery status, String location, String phone) {
        this.id = id;
        this.SaleOfDate = DATE_FORMAT.format(saleOfDate);
        this.username = username;
        this.totalPrice = totalPrice;
        this.status = status.name();
        this.location = location;
        this.phone = phone;
    }

    public void setSaleOfDate(Date saleOfDate) {
        SaleOfDate = DATE_FORMAT.format(saleOfDate);
    }

    public void setUsername(User username) {
        this.username = username.getEmail();
    }

    public void setStatus(StatusDelivery status) {
        this.status = status.name();
    }
}
