package com.organic.shop.Dtos;

import com.organic.shop.entities.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubMenuDto {
    private Long id;
    private String name;
    private String link;
    private Long idMenu;

//    public void setIdMenu(Menu m) {
//        this.idMenu = m.getId();
//    }
    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }
}
