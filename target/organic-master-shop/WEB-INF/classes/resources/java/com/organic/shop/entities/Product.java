package com.organic.shop.entities;

import com.organic.shop.utils.Shipping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbProduct")
@NamedQuery(name = "Product.getProductsAccordingCategory",
        query = "Select p from Product p join p.category c " +
                "where c.id=:id"
)
@NamedQuery(name = "Product.findByKeyWordAndCategory",
            query ="Select p from Product p join p.category c " +
                    "where c.id=:id and (p.name like  CONCAT('%', :key, '%') or p.description like  CONCAT('%', :key, '%')) " )

@NamedQuery(
        name = "Product.findByKey",
        query = "SELECT p FROM Product p" +
                " WHERE p.name LIKE CONCAT('%', :key, '%') OR p.description LIKE CONCAT('%', :key, '%')"
)

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String img;
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Date expireDate;
    private Integer untilInStock;
    private Date addAt;
    private String makeIn;
    private Double price;
    private Double weigh;
    @Enumerated(EnumType.STRING)
    private Shipping shipping;

    @ManyToMany(mappedBy = "products")
    private Set<Bill> bill = new HashSet<>();

    @ManyToMany(mappedBy ="products" )
    private Set<InvoiceForGoodsReceived> invoiceForGoodsReceives;
    @ManyToMany
    @JoinTable(name = "BillDetails",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id"))
    private Set<Bill> products = new HashSet<>();
}
