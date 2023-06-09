package com.organic.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbTokenConfirmAcc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery( name = "TokenConfirmAccount.findUserById",
        query = "Select u from TokenConfirmAccount t join t.user u " +
                " where t.id=:id"
)
public class TokenConfirmAccount {

    @SequenceGenerator(
            name = "confirmToken_user_sequence",
            sequenceName = "confirmToken_user_sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmToken_user_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmAt;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public TokenConfirmAccount(String token, LocalDateTime createAt, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.createAt = createAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }


}
