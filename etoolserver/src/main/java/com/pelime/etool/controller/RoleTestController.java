package com.pelime.etool.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;

@Controller
@RequestMapping("console")
public class RoleTestController {

    @RequestMapping("needRoleAdmin")
    public String needRoles(){
        return "needAdminRole";
    }
}
