package com.pelime.xtools.sysdao;


import com.pelime.xtools.sysmodel.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePermissionDao extends JpaRepository<SysRolePermission,Long> {
    List<SysRolePermission> findAllByRoleId(Long roleId);
    List<SysRolePermission> findAllByPermissionId(Long permissionId);
    List<SysRolePermission> findAllByPermissionId(Iterable<Long> ids);
    SysRolePermission findByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
