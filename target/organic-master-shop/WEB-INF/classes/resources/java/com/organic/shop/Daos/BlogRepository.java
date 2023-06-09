package com.organic.shop.Daos;

import com.organic.shop.entities.Blog;
import com.organic.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByKeyAndCategory(String keyWord, Long idBlog);

    List<Blog> findByKey(@Param("key")String key);

    User findAuthor(@Param("id") Long id);
}
