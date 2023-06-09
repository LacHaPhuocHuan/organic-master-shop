package com.organic.shop.Daos;

import com.organic.shop.entities.CategoryBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryBlogRepository extends JpaRepository<CategoryBlog, Long> {
}
