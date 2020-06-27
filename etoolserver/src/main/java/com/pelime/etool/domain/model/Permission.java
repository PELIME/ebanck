package com.pelime.etool.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_permission")
public class Permission extends BaseEntity {
    @Column(unique = true)
    private String name;                //权限名 唯一
    @Column(unique = true)
    private String url;                 //访问地址信息 唯一
    private String description;         //描述信息
}