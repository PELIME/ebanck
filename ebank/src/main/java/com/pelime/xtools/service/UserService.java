package com.pelime.xtools.service;

import com.pelime.xtools.dao.UserDao;
import com.pelime.xtools.model.User;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    BasicPasswordEncryptor basicPasswordEncryptor;

    //通过用户名登录
    public User loginByUsername(String username,String password){
        User user=userDao.findUserByUsername(username);
        if(user==null) return null;
        if(basicPasswordEncryptor.checkPassword(password,user.getPassword())){
            return user;
        }
        else return null;
    }

    //通过电话号码登录
    public User loginByPhoneNum(String phoneNum,String password){
        User user=userDao.findUserByPhoneNum(phoneNum);
        if(user==null) return null;
        if(basicPasswordEncryptor.checkPassword(password,user.getPassword())){
            return user;
        }
        else return null;
    }

    public User register(User user){
        user.setPassword(basicPasswordEncryptor.encryptPassword(user.getPassword()));
        return userDao.saveAndFlush(user);
    }
}
