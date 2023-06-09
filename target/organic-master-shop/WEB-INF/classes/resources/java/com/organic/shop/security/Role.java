package com.organic.shop.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Sets.newHashSet(
            Authentity.admin_read,
            Authentity.admin_write,
            Authentity.user_read,
            Authentity.user_write
    )),
    USER(Sets.newHashSet(
            Authentity.user_read,
            Authentity.user_write))
    ;
    private Set<Authentity> authentities;

    Role(Set<Authentity> authentities) {
        this.authentities = authentities;
    }

    public Set<? extends GrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authority
                =this.authentities.stream()
                .map(t->new SimpleGrantedAuthority(t.name()))
                .collect(Collectors.toSet());
        authority.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authority;
    }
}
