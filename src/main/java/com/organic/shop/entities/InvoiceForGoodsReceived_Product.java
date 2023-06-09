package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbInvoiceForGoodsReceived_Product")
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(
        name = "InvoiceForGoodsReceived_Product.findUnitSold"
        ,query = "Select Count(IP.quantity)" +
        " From InvoiceForGoodsReceived_Product IP join IP.product p " +
        " Where p.id=:id"
)
public class InvoiceForGoodsReceived_Product {

    @EmbeddedId
    private InvoiceForGoodsReceived_Product_Key id;

     @ManyToOne
    @MapsId("bill_id")
    @JoinColumn(name = "bill_id")
    InvoiceForGoodsReceived invoiceForGoodsReceived;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;


    private Integer quantity;
    private Double actualPrice;


}
