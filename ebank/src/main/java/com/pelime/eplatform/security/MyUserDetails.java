package com.pelime.eplatform.security;

import com.pelime.eplatform.domain.Role;
import com.pelime.eplatform.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class MyUserDetails implements UserDetails {

    private User user;

    public boolean isNonExpired() {
        return isNonExpired;
    }

    public void setNonExpired(boolean nonExpired) {
        isNonExpired = nonExpired;
    }

    private boolean isNonExpired=true;

    public MyUserDetails(User user){
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities=new LinkedList<>();
        Set<Role> roles=user.getRoles();
        for(Role r:roles){
            grantedAuthorities.add(new RoleGrantedAuthority(r.getRoleName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getUserState().getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getUserState().getAccountNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getUserState().getEnabled();
    }
}
