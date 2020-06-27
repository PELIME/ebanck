package com.pelime.etool.domain.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

    @Column(unique = true)
    private String name;                    //角色名 唯一
    private String description;             //描述信息
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Permission> permissions;   //一个用户角色对应多个权限
}