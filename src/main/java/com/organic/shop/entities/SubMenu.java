package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbSubMenu")
@NamedQuery(name = "SubMenu.getListSubMenuByIdMenu",
        query = "select s from SubMenu s join s.menu m where m.id=:idMenu"
)
@NamedQuery(name = "SubMenu.getIdMenu",
        query = "Select m " +
                "from SubMenu s join s.menu m where s.id=:id")
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String link;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
