package com.lynx.oauth.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authority implements GrantedAuthority {

    @Id
    private String name;

    public Authority() {
    }

    public Authority(GrantedAuthority prototype) {
        this();
        this.setAuthority(prototype.getAuthority());

    }

    public Authority(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public void setAuthority(String authority) {
        this.name = authority;
    }
}
