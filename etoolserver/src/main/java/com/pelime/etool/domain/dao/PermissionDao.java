package com.pelime.etool.domain.dao;

import com.pelime.etool.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends JpaRepository<Permission,Integer> {
    Permission findByName(String name);
}
