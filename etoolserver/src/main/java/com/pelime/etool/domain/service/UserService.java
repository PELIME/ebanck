package com.pelime.etool.domain.service;

import com.pelime.etool.domain.dao.UserDao;
import com.pelime.etool.domain.model.Role;
import com.pelime.etool.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Value("${etool.config.defaultSalt}")
    private String defaultSalt;

    public String getDefaultSalt(){
        return defaultSalt;
    }

    @Autowired
    UserDao userDao;

    public Boolean isExist(Integer id){
        return userDao.findById(id)==null;
    }

    public Boolean isExist(String username){
        return userDao.findByUsername(username)==null;
    }

    public User add(User user){
        Role role=new Role();
        role.setName("user");
        role.setDescription("默认角色");
        return this.add(user,role);
    }

    public User add(User user,Role role){
        List<Role> roleList=new ArrayList<>();
        roleList.add(role);
        return this.add(user,roleList);
    }

    public User add(User user,List<Role> roles){
        user.setRoles(roles);
        return userDao.save(user);
    }
}
