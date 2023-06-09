package com.organic.shop.serviceImpl;

import com.organic.shop.Daos.MenuRepository;
import com.organic.shop.Daos.SubMenuRepository;
import com.organic.shop.Dtos.MenuDto;
import com.organic.shop.Dtos.SubMenuDto;
import com.organic.shop.entities.Menu;
import com.organic.shop.entities.SubMenu;
import com.organic.shop.service.MenuService;
import com.organic.shop.utils.MenuAccordingRole;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    private ModelMapper mapper=new ModelMapper();
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    SubMenuRepository subMenuRepository;
    @Override
    public List<MenuDto> getMenu(MenuAccordingRole role) {
        List<Menu> menu=menuRepository.findByRole(role);
        List<MenuDto> menuDtos=new ArrayList<>();
        for (Menu m: menu
             ) {
            menuDtos.add(mapper.map(m,MenuDto.class));
        }
        return menuDtos;
    }
    @Override
    public HashMap<Long, Set<SubMenuDto>> getSubMenu() {
        List<SubMenu> subMenu=subMenuRepository.findAll();
//        // create a mapping configuration for SubMenu to SubMenuDto
//        TypeMap<SubMenu, SubMenuDto> typeMap = mapper.getTypeMap(SubMenu.class, SubMenuDto.class);
//        // configure the mapping
//        if (typeMap==null)
//            typeMap=mapper.createTypeMap(SubMenu.class, SubMenuDto.class);
//        typeMap.addMappings(mapper -> {
//                    mapper.map(src -> src.getMenu().getId(), SubMenuDto::setIdMenu);
//                });
//        mapper.getTypeMap(SubMenu.class, SubMenuDto.class).addMappings(mapper -> { mapper.map(src -> src.getMenu().getId(), SubMenuDto::setIdMenu); });

        HashMap<Long, Set<SubMenuDto> > dtoHashMap
                =new HashMap<>();
        List<SubMenuDto> subMenuDtos=subMenu.stream()
                        .map(t->(mapper.map(t, SubMenuDto.class)))

                .collect(Collectors.toList());
        System.out.println("MENU: "+subMenuDtos.size());

        for (SubMenuDto sd: subMenuDtos
             ) {
            System.out.println("sd id: "+sd.getId());
            try {
                sd.setIdMenu(subMenuRepository.getIdMenu(sd.getId()).orElseThrow().getId());
            }catch (Exception e){

            }
            if(!dtoHashMap.containsKey(sd.getIdMenu())) {
                System.out.println("key don't have: "+sd.getIdMenu());
                Set<SubMenuDto> dtos=new HashSet<>();
                dtos.add(sd);
                dtoHashMap.put(sd.getIdMenu(),dtos);
            }else {
                Set<SubMenuDto> dtos=dtoHashMap.get(sd.getIdMenu());
                dtos.add(sd);
                dtoHashMap.remove(sd.getIdMenu());
                dtoHashMap.put(sd.getIdMenu(),dtos);

            }
        }
        for (Map.Entry<Long, Set<SubMenuDto>> s : dtoHashMap.entrySet()
             ) {
            System.out.println("key: "+s.getKey() +"|value size :"+ s.getValue().size());
        }
        return dtoHashMap;
    }
}
