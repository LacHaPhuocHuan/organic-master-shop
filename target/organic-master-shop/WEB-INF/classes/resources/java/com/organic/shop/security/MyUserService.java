package com.organic.shop.security;

import com.organic.shop.Daos.UserRepository;
import com.organic.shop.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    User user;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user=userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        System.out.println("username" +username);
        System.out.println("pass"+user.getPassword());
        for (GrantedAuthority authoritySet: user.getRole().getAuthorities()
             ) {
            System.out.println("user role: "+authoritySet.toString() );
        }
        return new MyUserDetails(user.getEmail(),
                user.getPassword(),
                user.getRole(),
                isNonExpired(user.getCreateAt()),user.isNonBlocked(), true, true  );
    }

    private boolean isNonExpired(Date date) {
        Calendar now= Calendar.getInstance();
        Calendar d=Calendar.getInstance();
        d.setTime(date);
        if (Objects.isNull(date))
            return true;
        if((now.YEAR-d.YEAR)>10){
            return false;
        }
        return true;
    }

    public User getUser() {
        return user;
    }
}
