package com.pelime.etool.controller;

import com.pelime.etool.domain.dao.UserDao;
import com.pelime.etool.domain.model.User;
import com.pelime.etool.domain.service.UserService;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @Autowired
    UserService userService;

    /**
     * 登陆页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 退出 后跳转登陆页
     */
    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

    /**
     * 无权限跳转页面
     * @return
     */
    @RequestMapping("unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
    /**
     * 首页
     */
    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String RegisterPage(){
        return "register";
    }

    @PostMapping("/register")
    @Transactional
    public ModelAndView register(String username,String password){
        ModelAndView mv=new ModelAndView();
        if(userService.isExist(username)){
            mv.setViewName("/register");
            mv.addObject("error","已存在用户名");
            return mv;
        }
        String salt=userService.getDefaultSalt()+username+userService.getDefaultSalt();
        String ciphertext=new Md5Hash(password,salt,2).toString();
        User user=new User();
        user.setUsername(username);
        user.setPassword(ciphertext);
        //保存盐值
        user.setPasswordSalt(userService.getDefaultSalt());
        userService.add(user);
        mv.setViewName("/login");
        mv.addObject("message","注册成功");
        return mv;
    }
}