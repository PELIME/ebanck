package com.pelime.newbanck.api;

import com.pelime.newbanck.domain.User;
import com.pelime.newbanck.service.AuthenticationService;
import com.pelime.newbanck.service.UserService;
import com.pelime.newbanck.shiro.MyToken;
import com.pelime.newbanck.support.AESUtil;
import com.pelime.newbanck.support.EbanckCode;
import com.pelime.newbanck.support.EbanckHttpEntity;
import com.pelime.newbanck.support.StaticCommon;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
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

    /**
     *
     *
     * @param username
     * @param password
     * @param remember  代表前端传送过来的是否为加密后的密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password,
                        @RequestParam("remember") Boolean remember,@RequestParam("encode") Boolean encode ) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        //UsernamePasswordToken token = new UsernamePasswordToken(username, password);\
        if(encode){
            password=new String(AESUtil.decrypt(AESUtil.parseHexStr2Byte(password),username+StaticCommon.WEB_SECURITY_SECRECT));
        }
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
            if(remember){
                byte[] pwdbites=AESUtil.encrypt(password,username+StaticCommon.WEB_SECURITY_SECRECT);
                String encodePwd=AESUtil.parseByte2HexStr(pwdbites);
                return new EbanckHttpEntity(EbanckCode.SUCCESS,encodePwd);
            }
            return new EbanckHttpEntity(EbanckCode.SUCCESS,"null");
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

    public static String simpleEncrypt(String data){
        AesCipherService aesCipherService=new AesCipherService();
        aesCipherService.setKeySize(128);
        aesCipherService.generateNewKey();
        String var1= aesCipherService.encrypt("123456".getBytes(), StaticCommon.WEB_SECURITY_SECRECT.getBytes()).toHex();
        System.out.println(var1);
        String var2=new String(aesCipherService.decrypt(Hex.decode(var1),StaticCommon.WEB_SECURITY_SECRECT.getBytes()).getBytes());
        System.out.println(var2);
        System.out.println(new String(new AesCipherService().decrypt(Hex.decode(var1),StaticCommon.WEB_SECURITY_SECRECT.getBytes()).getBytes()));
        return "";
    }

    public static void main(String[] args) {
        simpleEncrypt("");
    }
}
