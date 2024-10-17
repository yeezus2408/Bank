package com.example.bank.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum roles implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
