package com.pelime.newbanck.dao;


import com.pelime.newbanck.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByPhone(String phone);
    User findByEmail(String email);


}
