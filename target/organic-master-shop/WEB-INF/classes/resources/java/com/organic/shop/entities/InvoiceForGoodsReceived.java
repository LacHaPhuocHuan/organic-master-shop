package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbInvoiceForGoodsReceived")
@NamedQuery(name = "InvoiceForGoodsReceived.findUserById",
query = "Select u from InvoiceForGoodsReceived i join i.user u" +
        " where i.id=:id ")
public class InvoiceForGoodsReceived {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime localDateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "InvoiceForGoodsReceived_Product",
            joinColumns = @JoinColumn(name = "invoiceForGoodsReceived_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products=new HashSet<>();




}
