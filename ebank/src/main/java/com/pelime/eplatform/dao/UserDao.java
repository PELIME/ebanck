package com.pelime.eplatform.dao;

import com.pelime.eplatform.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByPhone(String phone);
    User findByEmail(String email);


}
