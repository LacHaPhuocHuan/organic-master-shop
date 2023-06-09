package com.organic.shop.entities;

import com.organic.shop.security.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbUser")
//@NamedQuery( name = "User.findUserById",
//        query = "Select u from  User u join TokenConfirmAccount t  " +
//                " where t.id= :tokenId"
//)
public class User {
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="student_sequence" )
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String avatar;
    private String address;
    private boolean isNonBlocked;
    private Date CreateAt;
    private Date dateofBirth;

}
