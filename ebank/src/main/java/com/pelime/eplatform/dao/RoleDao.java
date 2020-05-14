package com.pelime.eplatform.dao;

import com.pelime.eplatform.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Long> {
}
