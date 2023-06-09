package com.organic.shop.Daos;

import com.organic.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsAccordingCategory(@Param("id")Long id);

    List<Product> findByKeyWordAndCategory(@Param("key") String key,@Param("id") Long id);

    List<Product> findByKey(@Param("key") String key);
//    Product findByName(String name);

}
