package com.organic.shop.Daos;

import com.organic.shop.entities.InvoiceForGoodsReceived_Product;
import com.organic.shop.entities.InvoiceForGoodsReceived_Product_Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository  extends JpaRepository<InvoiceForGoodsReceived_Product, InvoiceForGoodsReceived_Product_Key> {
    Integer findUnitSold(@Param("id") Long id);
}
