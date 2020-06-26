package com.pelime.newbanck.controller;

import com.pelime.newbanck.shiro.MyToken;
import com.pelime.newbanck.support.EbanckCode;
import com.pelime.newbanck.support.EbanckHttpEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeIndexController {



    @RequestMapping({"index","/"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String defaultLogin() {
        return "login";
    }

    @RequestMapping(value = "/wae", method = RequestMethod.GET)
    public String worryAboutEBank(){
        return "ebank-spend";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        Subject subject = SecurityUtils.getSubject();
        MyToken token=new MyToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            mv.addObject("error","未知账户");
            token.clear();
        } catch (IncorrectCredentialsException ice) {
            mv.addObject("error","密码不正确");
            token.clear();
        } catch (LockedAccountException lae) {
            mv.addObject("error","账户已锁定");
            token.clear();
        } catch (ExcessiveAttemptsException eae) {
            mv.addObject("error","账户已锁定");
            token.clear();
        } catch (AuthenticationException ae) {
            mv.addObject("error","用户名或密码不正确！");
            token.clear();
        }
        if (subject.isAuthenticated()) {
            mv.setViewName("redirect:index");
        }else {
            token.clear();
            mv.addObject("error","用户名或密码不正确！");
        }
        return mv;
    }

}
