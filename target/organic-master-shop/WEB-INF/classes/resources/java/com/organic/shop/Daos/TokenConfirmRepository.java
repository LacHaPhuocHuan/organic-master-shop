package com.organic.shop.Daos;

import com.organic.shop.entities.TokenConfirmAccount;
import com.organic.shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenConfirmRepository extends JpaRepository<TokenConfirmAccount, Long> {
    Optional<TokenConfirmAccount> findByToken(String token);

    Optional<User> findUserById(@Param("id") Long id);
}
