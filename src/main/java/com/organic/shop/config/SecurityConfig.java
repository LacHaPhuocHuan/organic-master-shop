package com.organic.shop.config;

import com.organic.shop.security.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Autowired
    MyUserService service;


    @Bean
    BCryptPasswordEncoder passwordEncoder(){return  new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    DaoAuthenticationProvider authenticationProvider(BCryptPasswordEncoder encoder){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //
                .antMatchers("/api/v/auth/**", "/assets/**", "/api/u/**").permitAll()
                .antMatchers("/api/v/u/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/v/auth/login")
                .loginProcessingUrl("/api/v/auth/login/sign-in")
                .defaultSuccessUrl("/api/u/home")
                .failureUrl("/api/v/auth/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/api/v/auth/login/logout")
                .logoutSuccessUrl("/api/v/auth/login")
                .and()
                .exceptionHandling().accessDeniedPage("/api/u/home")
        ;

        http.csrf().disable();

        return http.build();

    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


}
