package com.pelime.newbanck.shiro;

import com.pelime.newbanck.domain.User;
import com.pelime.newbanck.service.AuthenticationService;
import com.pelime.newbanck.support.StaticCommon;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {


    public AuthenticationService authenticationService;

    public CustomRealm(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("*");
        info.setStringPermissions(stringSet);
        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if(token.getClass().isAssignableFrom(MyToken.class)) return true;
        return super.supports(token);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd =authenticationToken.getCredentials().toString();
        //根据用户名从数据库获取密码
        User user=authenticationService.dbuser(userName);
        if (user == null) {
            throw new AccountException("用户名不正确");
        }
//        else if(!userPwd.equals(user.getPassword())){
//            throw new AccountException("密码不正确");
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(userName + StaticCommon.SALT), getName());
    }
}
