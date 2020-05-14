package com.pelime.xtools.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class EbanckAuthenticationDetails implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    //id
    private long id;
    //账户名
    private String username;
    //性别
    private String sex;
    //头像url
    private String iconPath;
    //登陆的客服端
    private String loginClientId;
    //身份证号
    private String cardNumber;


    //会员信息
    private String memberGrade;

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    private List<GrantedAuthority> authorities;

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    //积分
    private Long integral;

    public EbanckAuthenticationDetails(){}

    public EbanckAuthenticationDetails(HashMap hashMap) {
        this((Integer)hashMap.get("id"),(String) hashMap.get("username"),(String)hashMap.get("sex"),
                (String)hashMap.get("iconPath"),(String)hashMap.get("loginClientId"),
                (String)hashMap.get("cardNumber"));
    }

    public EbanckAuthenticationDetails(long id, String username, String sex,
                                       String iconPath, String loginClientId, String cardNumber) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.iconPath = iconPath;
        this.loginClientId = loginClientId;
        this.cardNumber = cardNumber;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginClientId() {
        return loginClientId;
    }

    public void setLoginClientId(String loginClientId) {
        this.loginClientId = loginClientId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }
}
