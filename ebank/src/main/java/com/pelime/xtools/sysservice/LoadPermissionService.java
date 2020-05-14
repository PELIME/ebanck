package com.pelime.xtools.sysservice;

import com.pelime.xtools.sysdao.SysPermissionDao;
import com.pelime.xtools.sysdao.SysRoleDao;
import com.pelime.xtools.sysdao.SysRolePermissionDao;
import com.pelime.xtools.sysmodel.SysPermission;
import com.pelime.xtools.sysmodel.SysRole;
import com.pelime.xtools.sysmodel.SysRolePermission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class LoadPermissionService {

    @Resource
    SysRoleDao sysRoleDao;
    @Resource
    SysRolePermissionDao sysRolePermissionDao;
    @Resource
    SysPermissionDao sysPermissionDao;


    @Value("${hospital.clientId}")
    private String clientId;

    @Transactional
    public Map<RequestMatcher,Collection<ConfigAttribute>> loadAll(){
        Map<RequestMatcher,Collection<ConfigAttribute>> map=new HashMap<>();
        List<SysPermission> allPermission= sysPermissionDao.findAllByClientId(clientId);
        Iterator<SysPermission> var1=allPermission.iterator();
        while (var1.hasNext()){
            SysPermission permission=var1.next();
            RequestMatcher matcher=new AntPathRequestMatcher(permission.getUrl());
            Iterator<SysRole> var2=permission.getRoles().iterator();
            List<ConfigAttribute> configAttributes=new ArrayList<>();
            while (var2.hasNext()){
                SysRole role=var2.next();
                configAttributes.add(new SecurityConfig("ROLE_"+role.getName()));
            }
            map.put(matcher,configAttributes);
        }
        return map;
    }
    public Map<String,Set<String>> load()
    {
        Map<String,Set<String>> urlRoleMap =new HashMap<>();
        Set<String> permitAll=new HashSet<>();
        List<SysRole> roles=sysRoleDao.findAll();
        if(roles.size()>0)
        {
            for(SysRole r:roles)
            {
                List<SysRolePermission> rolePermissions=sysRolePermissionDao.findAllByRoleId(r.getId());
                List<Long> permissionIds=new ArrayList<>();
                for(SysRolePermission rp:rolePermissions)
                {
                    permissionIds.add(rp.getPermissionId());
                }
                List<SysPermission> permissions=sysPermissionDao.findAllById(permissionIds);
                Set<String> urls=new HashSet<>();
                for(SysPermission permission:permissions)
                {
                    urls.add(permission.getUrl());

                }
                if(r.getName().equals("USER"))
                {
                    permitAll=urls;
                    continue;
                }
                urlRoleMap.put(r.getName(),urls);
            }
        }
        urlRoleMap.put("permitAll",permitAll);
        return urlRoleMap;
    }
}
