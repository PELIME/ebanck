package com.pelime.newbanck.api;

import com.pelime.newbanck.domain.User;
import com.pelime.newbanck.service.AuthenticationService;
import com.pelime.newbanck.service.UserService;
import com.pelime.newbanck.shiro.MyToken;
import com.pelime.newbanck.support.EbanckCode;
import com.pelime.newbanck.support.EbanckHttpEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);\
        MyToken token=new MyToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"未知账户");
        } catch (IncorrectCredentialsException ice) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"密码不正确");
        } catch (LockedAccountException lae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"用户名或密码不正确！");
        }
        if (subject.isAuthenticated()) {
            return new EbanckHttpEntity(EbanckCode.SUCCESS,"登录成功");
        } else {
            token.clear();
            return new EbanckHttpEntity(EbanckCode.BAD_CERTIFIED,"登录失败");
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Object regiest(@RequestParam("principal") String principals,@RequestParam("password") String password){
        User user =authenticationService.register(principals,password);
        if(user==null){
            return new EbanckHttpEntity(EbanckCode.USER_EXISTS,"用户已存在");
        }
        userService.register(user);
        return new EbanckHttpEntity(EbanckCode.USER_EXISTS,"注册成功");
    }
}
