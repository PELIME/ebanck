package com.pelime.xtools.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class RoleBasedVoter implements AccessDecisionVoter<Object> {
    private String rolePrefix = "ROLE_";

    public RoleBasedVoter() {
    }
    public String getRolePrefix() {
        return this.rolePrefix;
    }
    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute.getAttribute() != null && attribute.getAttribute().startsWith(this.getRolePrefix());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        if (authentication == null) {
            return ACCESS_DENIED;
        } else {
            int result = ACCESS_ABSTAIN;
            Collection<? extends GrantedAuthority> authorities = this.extractAuthorities(authentication);
            Iterator var6 = collection.iterator();
            while (true) {
                ConfigAttribute attribute;
                do {
                    if (!var6.hasNext())
                        return result;
                    attribute = (ConfigAttribute) var6.next();
                } while (!this.supports(attribute));

                result = ACCESS_DENIED;
                Iterator var8 = authorities.iterator();

                while (var8.hasNext()) {
                    GrantedAuthority authority = (GrantedAuthority)var8.next();
                    if (attribute.getAttribute().equals(authority.getAuthority())||
                            attribute.getAttribute().equals("ROLE_USER")) //自定义所以用户默认角色
                    {
                        return 1;
                    }
                }
            }
        }
    }
    Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }

}

