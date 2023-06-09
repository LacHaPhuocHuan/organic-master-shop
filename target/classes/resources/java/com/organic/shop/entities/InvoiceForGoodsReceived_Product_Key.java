package com.organic.shop.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class InvoiceForGoodsReceived_Product_Key implements Serializable {
    private static final long serialVersionUID = -2525241308011753611L;
    @Column(name = "invoiceForGoodsReceived_id")
    Long invoiceForGoodsReceivedId;
    @Column(name = "product_id")
    Long productId;

}
