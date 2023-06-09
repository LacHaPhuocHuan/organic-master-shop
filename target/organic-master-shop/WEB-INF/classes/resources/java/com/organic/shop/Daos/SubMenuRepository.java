package com.organic.shop.Daos;

import com.organic.shop.Dtos.SubMenuDto;
import com.organic.shop.entities.Menu;
import com.organic.shop.entities.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
//@Transactional
public interface SubMenuRepository extends JpaRepository<SubMenu,Long> {
    List<SubMenu> getListSubMenuByIdMenu(Long idMenu);

    Optional<Menu> getIdMenu(Long id);

//    @Query(value = "select s.id, s.name, s.link, m.id" +
//            "   from tbsubmenu s join tbmenu m on s.menu_id=m.id ", nativeQuery = true)
//    List<SubMenuDto> getSubTDO();

}
