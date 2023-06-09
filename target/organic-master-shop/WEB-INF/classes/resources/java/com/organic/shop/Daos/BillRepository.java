package com.organic.shop.Daos;

import com.organic.shop.Dtos.BillDto;
import com.organic.shop.entities.Bill;
import com.organic.shop.entities.Product;
import com.organic.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    User findUserById(@Param("id") Long id);
}
