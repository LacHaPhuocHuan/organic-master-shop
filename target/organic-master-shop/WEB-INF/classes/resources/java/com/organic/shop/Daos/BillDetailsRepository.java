package com.organic.shop.Daos;

import com.organic.shop.entities.Bill;
import com.organic.shop.entities.BillDetails;
import com.organic.shop.entities.BillDetailsKey;
import com.organic.shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface BillDetailsRepository extends JpaRepository<BillDetails, BillDetailsKey> {
    List<Product> getAndSortAccodingQuantity();
    Double totalPrice(@Param("id") Long id);


//    List<Bill> finByBillId(Long id);
}
