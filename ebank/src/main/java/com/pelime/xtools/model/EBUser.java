package com.pelime.xtools.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 手机银行用户表
 */
@Entity
public class EBUser {
    @Id
    private Long id;
    //机构号
    private int organizationNum;
    //用户名
    private String name;
    //电话号码
    private String phoneNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrganizationNum() {
        return organizationNum;
    }

    public void setOrganizationNum(int organizationNum) {
        this.organizationNum = organizationNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
