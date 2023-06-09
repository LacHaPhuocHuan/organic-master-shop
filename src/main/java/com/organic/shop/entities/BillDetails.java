package com.organic.shop.entities;

import com.organic.shop.utils.StatusDelivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbBillDetails")
@NamedQuery(name = "BillDetails.getAndSortAccodingQuantity", query = "Select p " +
        "From BillDetails bd join bd.product p"
        +
        " GROUP BY p.id " +
        "ORDER BY SUM(bd.quantity) DESC"
        )
@NamedQuery(name = "BillDetails.totalPrice",query = "Select SUM(bd.actualPrice) from BillDetails bd join bd.bill b " +
        "where b.id=:id")
public class BillDetails {
    @EmbeddedId
     BillDetailsKey id;
    @ManyToOne
    @MapsId("bill_id")
    @JoinColumn(name = "bill_id")
    Bill bill;
    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;
    private Integer quantity;
    private Double actualPrice;

    public BillDetails(BillDetailsKey id, Bill bill, Product product, Integer quantity) {
        this.id = id;
        this.bill = bill;
        this.product = product;
        this.quantity = quantity;
        this.actualPrice = quantity*product.getPrice();
    }
}
