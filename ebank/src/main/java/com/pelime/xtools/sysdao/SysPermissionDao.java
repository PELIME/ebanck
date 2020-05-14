package com.pelime.xtools.sysdao;



import com.pelime.xtools.sysmodel.SysPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysPermissionDao extends JpaRepository<SysPermission,Long> {
    SysPermission findSysPermissionByName(String name);

    @Query("SELECT p FROM SysPermission p WHERE p.url LIKE '%?1%'")
    List<SysPermission> findAllLikeUrl(String url);

    List<SysPermission> findAllByClientId(String clientId);

    Page<SysPermission> findAllByClientId(String clientId, Pageable pageable);

}
