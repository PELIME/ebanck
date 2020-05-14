package com.pelime.xtools.sysdao;


import com.pelime.xtools.sysmodel.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole,Long> {
    List<SysUserRole> findAllByUserId(Long userId);
    List<SysUserRole> findAllByRoleId(Long roleId);
    SysUserRole findByUserIdAndRoleId(Long userId, Long roleId);

}
