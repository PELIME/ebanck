package com.pelime.xtools.sysservice;

import com.pelime.xtools.sysmodel.SysRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EbanckUserDetail implements UserDetails  {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private List<GrantedAuthority>  authorities;

    public EbanckUserDetail(Long id, String username, String password, List<SysRole> roles, String email, String phone) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        authorities=new ArrayList<>();
        for(SysRole role:roles)
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
