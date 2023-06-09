package com.organic.shop.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BillDetailsKey implements Serializable {
    @Column(name = "bill_id")
     Long billId;

    @Column(name = "product_id")
     Long productId;


}
