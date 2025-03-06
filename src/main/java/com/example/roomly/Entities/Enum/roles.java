package com.example.roomly.Entities.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum roles implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
