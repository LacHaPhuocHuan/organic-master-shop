package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbReview")
@NamedQuery(name = "Review.findAverageStar",
        query = "select AVG(r.star)" +
                " From Review r join r.product p " +
                "where p.id=:id"
)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String comment;
    private Integer star;
    private String img;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    public void setStar(Integer start) {
        if(start>5)
            this.star = 5;
        else
        if (start<1) this.star=1;
        else this.star=start;
    }

    public Integer getStar() {
        if(star>5)
            return  5;
        else
            if (star<1) return 1;
            else return star;

    }
}

