package com.pelime.xtools.sysservice;

import com.pelime.xtools.sysmodel.SysRole;
import com.pelime.xtools.sysmodel.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EbanckUserDetailService implements UserDetailsService  {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {
        SysUser user= sysUserService.getOne(var1);
        if(user==null)
        {
            throw new UsernameNotFoundException(var1);
        }
        List<SysRole> roles= sysUserService.getRoles(user.getId());

        return new EbanckUserDetail(user.getId(),user.getAccountName(),user.getPassword(),roles,user.getEmail(),user.getPhone());
    }
}
