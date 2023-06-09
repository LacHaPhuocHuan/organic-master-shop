package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbBlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Blog.findByKeyAndCategory", query = "" +
        "Select b from Blog b join b.category c " +
        "where c.id=:id AND ( b.name LIKE  CONCAT('%', :key, '%') OR b.title LIKE  CONCAT('%', :key, '%') OR b.content LIKE  CONCAT('%', :key, '%'))")
//@NamedQuery(name = "Blog.findByKey", query = "" +
//        "Select b from Blog b " +
//        "where b.name LIKE %:key% OR b.title LIKE  CONCAT('%', :key, '%') OR b.content LIKE  CONCAT('%', :key, '%')")
@NamedQuery(name = "Blog.findByKey", query = "" +
        "Select b from Blog b " +
        "where b.name LIKE CONCAT('%', :key, '%') OR b.title LIKE  CONCAT('%', :key, '%') OR b.content LIKE  CONCAT('%', :key, '%')")

@NamedQuery(name = "Blog.findAuthor", query = "" +
        "Select u from Blog b join b.user u " +
        "where b.id=:id")

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String title;
    private String content;
    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private String img;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryBlog category;

}
