package com.pelime.eplatform.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleGrantedAuthority implements GrantedAuthority {
    private String roleAuth;

    public RoleGrantedAuthority(String roleAuth) {
        this.roleAuth = "ROLE_"+roleAuth;
    }

    @Override
    public String getAuthority() {
        return roleAuth;
    }
}
