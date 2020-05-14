package com.pelime.eplatform.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private User user;

    //账号是否过期
    private Boolean isAccountNonExpired;

    //账号是否锁定
    private Boolean isAccountNonLocked;

    //是否激活
    private Boolean isEnabled;


}
