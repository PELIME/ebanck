package com.pelime.eplatform.security;

import com.pelime.eplatform.dao.UserDao;
import com.pelime.eplatform.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s==null||s.equals("")) return null;
        s=s.trim();
        User usertmp=null;
        //电话号码正则表达
        String phoneRegex="^1[0-9]\\d{10}$";
        //邮箱正则表达
        String emailRegex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        if(s.matches(phoneRegex)){
             usertmp=userDao.findByPhone(s);
        }
        else if(s.matches(emailRegex)){
            usertmp=userDao.findByEmail(s);
        }
        else {
            usertmp=userDao.findByUsername(s);
        }
        if(usertmp==null) return null;
        else
            return new MyUserDetails(usertmp);
    }
}
