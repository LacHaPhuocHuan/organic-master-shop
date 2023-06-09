package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tbDiscountCode")
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String code;
    private Date createAt;
    private Date expiredAt;
    private Integer percentDiscount;

}
