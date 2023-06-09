package com.organic.shop.service;

import com.organic.shop.Dtos.MenuDto;
import com.organic.shop.Dtos.SubMenuDto;
import com.organic.shop.entities.Menu;
import com.organic.shop.entities.SubMenu;
import com.organic.shop.utils.MenuAccordingRole;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public interface MenuService {
    List<MenuDto> getMenu(MenuAccordingRole role);

    HashMap<Long, Set<SubMenuDto>> getSubMenu();
}
