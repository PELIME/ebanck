package com.pelime.newbanck.controller;

import com.pelime.newbanck.dao.UserDao;
import com.pelime.newbanck.domain.User;
import com.pelime.newbanck.service.AuthenticationService;
import com.pelime.newbanck.service.UserService;
import com.pelime.newbanck.shiro.MyToken;
import com.pelime.newbanck.support.StaticCommon;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeIndexController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "desktop/login";
    }

    @RequestMapping(value = "/plogin", method = RequestMethod.GET)
    public String phoneLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);\
        MyToken token=new MyToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return "未知账户";
        } catch (IncorrectCredentialsException ice) {
            ice.printStackTrace();
            return "密码不正确";
        } catch (LockedAccountException lae) {
            return "账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            return "用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            return "用户名或密码不正确！";
        }
        if (subject.isAuthenticated()) {
            return "登录成功";
        } else {
            token.clear();
            return "登录失败";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/regiest",method = RequestMethod.POST)
    public String regiest(@RequestParam("principal") String principals,@RequestParam("password") String password){
        User user =authenticationService.regiest(principals,password);
        if(user==null){
            return "用户已存在";
        }
        userService.regiest(user);
        return "注册成功";
    }
}
