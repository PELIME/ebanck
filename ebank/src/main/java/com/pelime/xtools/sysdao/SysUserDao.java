package com.pelime.xtools.sysdao;


import com.pelime.xtools.sysmodel.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SysUserDao extends JpaRepository<SysUser,Long> {
    SysUser findSysUserById(Long id);
    SysUser findSysUserByAccountName(String account);
    Page<SysUser> findAllByRealName(Pageable pageable, String realName);
    Page<SysUser> findAllByAccountNameLike(String accountName, Pageable pageable);
    Page<SysUser> findAllByPhone(Pageable pageable, String phone);
    Page<SysUser> findAllByEmail(Pageable pageable, String email);
    Page<SysUser> findAllByIdCardNo(Pageable pageable, String idCardNo);
    Page<SysUser> findAllByBirthday(Pageable pageable, LocalDate birthday);
    Page<SysUser> findAll(Pageable pageable);
    @Query("select count(u.id) from  SysUser u")
    int queryAllCount();

    @Query("select u from SysUser as u where u.accountName like %?1%")
    Page<SysUser> findByAccountNameLike(String accountName, Pageable pageable);

    @Query("select u from SysUser as u inner join SysUserRole as ur on ur.userId=u.id " +
            "where u.accountName like %?1% and ur.roleId=?2")
    Page<SysUser> findByAccountNameLikeAndRoleId(String account, Long roleId, Pageable pageable);

    @Query("select u from SysUser as u inner join SysUserRole as ur " +
            "on ur.userId=u.id where ur.roleId=?1")
    Page<SysUser> findByRoleId(Long roleId, Pageable pageable);
}
