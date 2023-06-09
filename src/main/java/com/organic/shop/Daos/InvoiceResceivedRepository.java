package com.organic.shop.Daos;

import com.organic.shop.Dtos.UserDto;
import com.organic.shop.entities.InvoiceForGoodsReceived;
import com.organic.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceResceivedRepository extends JpaRepository<InvoiceForGoodsReceived ,Long> {

    User findUserById(@Param("id") Long id);
}
