package com.organic.shop.Dtos;

import com.organic.shop.utils.Shipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
public class ProductSaleDto {
    private Long id;
    private String name;
    private String img;
    private String description;
    private Long idCategory;
    private Date expireDate;
    private Integer untilInStock;
    private Date addAt;
    private String makeIn;
    private Double price;
    private Double weigh;
    private Shipping shipping;

    private Double salePercent;

    public ProductSaleDto(Long id, String name, String img, Long idCategory, Date expireDate, Integer untilInStock, Date addAt, String makeIn, Double price, Double weigh, Shipping shipping) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.idCategory = idCategory;
        this.expireDate = expireDate;
        this.untilInStock = untilInStock;
        this.addAt = addAt;
        this.makeIn = makeIn;
        this.price = price;
        this.weigh = weigh;
        this.shipping = shipping;
        this.salePercent = generateSalePercent();
    }

    public ProductSaleDto(Long id, String name, String img, Long idCategory, Date expireDate, Integer untilInStock, Date addAt, String makeIn, Double price, Double weigh, Shipping shipping, Double price1) {
    }

    public void setSalePercent(Double salePercent) {
        this.salePercent = salePercent;
    }

    private Double generateSalePercent(){
        Calendar ca1= Calendar.getInstance();
        ca1.setTime(this.addAt);
        Double percent=0.0;
        if(ca1.HOUR>(24*4) && ca1.HOUR<(24*6)){
            percent=20.0;
        }else if (ca1.HOUR> 24*6 ){
            percent=50.0;
        }
        return percent;
    }

}
