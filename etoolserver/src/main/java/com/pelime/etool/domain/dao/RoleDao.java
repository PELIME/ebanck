package com.pelime.etool.domain.dao;

import com.pelime.etool.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
