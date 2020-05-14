package com.pelime.xtools.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class EbanckAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
