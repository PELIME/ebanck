package com.pelime.xtools.sysservice;

import com.pelime.xtools.exception.DataNotFoundException;
import com.pelime.xtools.sysdao.SysRoleDao;
import com.pelime.xtools.sysdao.SysUserDao;
import com.pelime.xtools.sysdao.SysUserRoleDao;
import com.pelime.xtools.sysmodel.SysRole;
import com.pelime.xtools.sysmodel.SysUser;
import com.pelime.xtools.sysmodel.SysUserRole;
import com.pelime.xtools.util.JSONBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysUserService {
    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysUserRoleDao sysUserRoleDao;

    @Autowired
    SysRoleDao sysRoleDao;

    public SysUser getOne(Long id)
    {
        SysUser user=sysUserDao.findSysUserById(id);
        if(user==null) throw new DataNotFoundException();
        return user;
    }

    public SysUser getOne(String accountName)
    {
        SysUser user=sysUserDao.findSysUserByAccountName(accountName);
        if(user==null) throw new DataNotFoundException();
        return user;
    }

    public List<SysUser> getAllByRealName(String realName, Pageable pageable)
    {
        Page<SysUser> userstmp=sysUserDao.findAllByRealName(pageable,realName);
        List<SysUser> users=userstmp.getContent();
        if(users.size()<=0)  throw new DataNotFoundException();
        return users;
    }
    public List<SysUser> getAllByBirthday(LocalDate birthday, Pageable pageable)
    {
        Page<SysUser> userstmp=sysUserDao.findAllByBirthday(pageable,birthday);
        List<SysUser> users=userstmp.getContent();
        if(users.size()<=0)  throw new DataNotFoundException();
        return users;
    }

    public List<SysRole> getRoles(Long userId)
    {
        List<SysUserRole> rolestmp=sysUserRoleDao.findAllByUserId(userId);
        if(rolestmp.size()<=0) return null;
        List<Long> roleIds=new ArrayList<>();
        for(SysUserRole var1:rolestmp)
        {
            roleIds.add(var1.getRoleId());
        }
        List<SysRole> roles=sysRoleDao.findAllById(roleIds);
        if(roles.size()<=0) throw new DataNotFoundException();
        return roles;
    }

    @Transactional
    public SysUser save(SysUser user,int roleId) throws Exception {
        SysRole role=sysRoleDao.findById(0l+roleId).get();
        if(role==null){
            throw new Exception("角色不存在");
        }
        SysUser saveUser=sysUserDao.save(user);
        SysUserRole userRole=new SysUserRole();
        userRole.setUserId(saveUser.getId());
        userRole.setRoleId(role.getId());
        sysUserRoleDao.save(userRole);
        return saveUser;
    }

    public Object queryUser(String condition,String value,Pageable pageable){
        Page<SysUser> users;
        if(condition==null||condition.equals("")){
            users= sysUserDao.findAll(pageable);
        }
        else {
            if(condition.equals("accountName")){
                users=sysUserDao.findByAccountNameLike(value,pageable);
            }
            else if(condition.equals("phone")){
                users=sysUserDao.findAllByPhone(pageable,value);
            }else if(condition.equals("email")){
                users=sysUserDao.findAllByEmail(pageable,value);
            }else if(condition.equals("idCardNum")){
                users=sysUserDao.findAllByIdCardNo(pageable,value);
            }else {
                return null;
            }
        }
        Integer pageCount=users.getTotalPages();
        Integer currentPage=pageable.getPageNumber();
        List<SysUser> userList=users.getContent();
        List<Map<String,Object>> result=new ArrayList<>();
        for(SysUser u:userList){
            Map<String,Object> userinfo= combinationUser(u);
            result.add(userinfo);
        }
        Object res= JSONBuilder.create().element("pageCount",pageCount)
                .element("currentPage",currentPage)
                .element("users",result).toJSON();
        return res;
    }

    public Object getUserRoleInfo(String account,String roleName,Pageable pageable){

        SysRole role = sysRoleDao.findByName(roleName);
        List<Object> userRoleList=new ArrayList<>();
        Page<SysUser> users;
        if(account!=null&&!account.equals("")&&role!=null){
            users = sysUserDao.findByAccountNameLikeAndRoleId(account, role.getId(), pageable);
        }else if((account==null||account.equals(""))&&role!=null) {
            users=sysUserDao.findByRoleId(role.getId(),pageable);
        }else if(account!=null&&!account.equals("")&&role==null){
            users=sysUserDao.findByAccountNameLike(account,pageable);
        }
        else {
            users=sysUserDao.findAll(pageable);
        }
        for(SysUser u:users){
            List<SysRole> roles = sysRoleDao.findByUserId(u.getId());
            StringBuilder sb=new StringBuilder();
            for(SysRole r:roles){
                sb.append(r.getName()+",");
            }
            String roleString=sb.toString();
            userRoleList.add(JSONBuilder.create().element("id",u.getId())
            .element("accountName",u.getAccountName())
            .element("roles",roleString.length()>0?roleString.substring(0,roleString.length()-1):"").toMap());
        }
         return JSONBuilder.create().element("type", "success").element("info",userRoleList)
                 .element("pageCount",users.getTotalPages())
                 .toJSON();
    }
    private Map<String,Object>  combinationUser(SysUser u){
        return JSONBuilder.create().element("id",u.getId())
                .element("accountName",u.getAccountName())
                .element("phone",u.getPhone())
                .element("realName",u.getRealName())
                .element("idCardNo",u.getIdCardNo())
                .element("sex",u.getSex())
                .element("age",u.getAge())
                .element("address",u.getAddress())
                .element("balance",u.getBalance())
                .element("memberIntegral",u.getMemberIntegral())
                .element("email",u.getEmail())
                .element("state",u.getState())
                .toMap();
    }
    public SysUser save(SysUser user){
        return sysUserDao.saveAndFlush(user);
    }

    public void delete(SysUser user){
        sysUserDao.delete(user);
    }
}
