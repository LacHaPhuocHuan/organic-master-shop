package com.organic.shop.Daos;

import com.organic.shop.entities.Menu;
import com.organic.shop.utils.MenuAccordingRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRole(MenuAccordingRole user);
}
