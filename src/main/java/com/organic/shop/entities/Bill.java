package com.organic.shop.entities;

import com.organic.shop.utils.StatusDelivery;
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
@Table(name = "tbBill")
@NamedQuery(name = "Bill.findUserById"
, query = "Select u " +
        "From Bill b join b.user u where b.id=:id")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date SaleOfDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "BillDetails",
            joinColumns = @JoinColumn(name = "bill_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private StatusDelivery statusDelivery;
    private  String address;
    private Long phone;


}
