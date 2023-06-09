package com.organic.shop.security;

public enum Authentity {
    admin_read("admin::read"),
    user_read("user::read"),
    admin_write("user::write"),
    user_write("user::write");

    private String nameAuth;

    Authentity(String nameAuth) {
        this.nameAuth = nameAuth;
    }

}
