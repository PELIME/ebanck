package com.pelime.xtools.dao;

import com.pelime.xtools.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
    User findUserByPhoneNum(String phoneNum);
}
