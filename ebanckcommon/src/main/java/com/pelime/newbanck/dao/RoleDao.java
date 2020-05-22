package com.pelime.newbanck.dao;


import com.pelime.newbanck.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
}
