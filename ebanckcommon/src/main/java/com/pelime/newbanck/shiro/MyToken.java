package com.pelime.newbanck.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class MyToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
    private String username;
    private String password;
    private boolean rememberMe;
    private String host;

    public MyToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        password=null;
        if (this.password != null) {
            password="";
            this.password = null;
        }

    }
}
