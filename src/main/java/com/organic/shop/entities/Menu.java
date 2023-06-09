package com.organic.shop.entities;

import com.organic.shop.utils.MenuAccordingRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbMenu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String link;
    @Enumerated(EnumType.STRING)
    private MenuAccordingRole role ;

}
