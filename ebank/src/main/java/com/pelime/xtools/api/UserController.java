package com.pelime.xtools.api;

import com.pelime.xtools.security.EbanckAuthenticationDetails;
import com.pelime.xtools.sysmodel.SysRole;
import com.pelime.xtools.sysmodel.SysUser;
import com.pelime.xtools.sysservice.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private Logger logger =LoggerFactory.getLogger(getClass());

    @Autowired
    SysUserService sysUserService;
    @RequestMapping("/user/me")
    public EbanckAuthenticationDetails user(OAuth2Authentication authentication) {
        String clientId=authentication.getOAuth2Request().getClientId();
        String name=authentication.getName();
        SysUser user=sysUserService.getOne(name);
        EbanckAuthenticationDetails details=new EbanckAuthenticationDetails();
        details.setId(user.getId());
        details.setUsername(user.getAccountName());
        details.setIconPath(user.getIconPath());
        details.setSex(user.getSex());
        details.setLoginClientId(clientId);
        details.setCardNumber(user.getIdCardNo());
        details.setIntegral(user.getMemberIntegral());
        details.setMemberGrade(user.getMemberGrade());
        List<SysRole> roles=sysUserService.getRoles(user.getId());
        List<GrantedAuthority>  authoritie=new ArrayList<>();
        for(SysRole role:roles)
        {
            authoritie.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        details.setAuthorities(authoritie);
        return details;
    }

}
