package com.pelime.xtools.security;

import com.pelime.xtools.sysservice.LoadPermissionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DatabaseFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final Map<RequestMatcher,Collection<ConfigAttribute>> requestMap;

    public DatabaseFilterInvocationSecurityMetadataSource(LoadPermissionService service) {
        requestMap=service.loadAll();
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        HttpServletRequest request=((FilterInvocation) o).getRequest();
        String url = fi.getRequestUrl();


        for(Map.Entry<RequestMatcher,Collection<ConfigAttribute>> entry:requestMap.entrySet()){
            if(entry.getKey().matches(request)){
                return entry.getValue();
            }
        }
        List<ConfigAttribute> defaultConfig=new ArrayList<>(1);
        defaultConfig.add(new SecurityConfig("IS_AUTHENTICATED_FULLY"));
        //  返回代码定义的默认配置
        return defaultConfig;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
