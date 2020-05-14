package com.pelime.xtools.sysdao;


import com.pelime.xtools.sysmodel.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface SysRoleDao extends JpaRepository<SysRole,Long> {
    SysRole findByName(String name);

    @Query(value = "select new map(r.id as id,r.name as name) from SysRole r")
    List<Map<String,Object>> fingAllSimplification();

    @Query("select r from SysRole as r inner join SysUserRole as ur on ur.roleId=r.id where ur.userId=?1")
    List<SysRole> findByUserId(Long userId);
}
