package com.pelime.newbanck.service;

import com.pelime.newbanck.dao.UserDao;
import com.pelime.newbanck.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User regiest(User user){
        return userDao.save(user);
    }
}
