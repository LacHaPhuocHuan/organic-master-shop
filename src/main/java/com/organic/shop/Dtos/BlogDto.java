package com.organic.shop.Dtos;

import com.organic.shop.entities.CategoryBlog;
import com.organic.shop.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Long id;
    private String name;
    private String title;
    private String content;
    private Date createAt;
    private Long idUser;
    private String img;
    private Long idCategoryBlog;

    public void setIdCategoryBlog(CategoryBlog idCategoryBlog) {
        this.idCategoryBlog = idCategoryBlog.getId();
    }
    public void setIdUser(User user) {
        this.idUser = user.getId();
    }
}
