package com.pelime.newbanck.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
public class UserState {

    @Id
    @GenericGenerator(name="foreignGenerator",strategy="foreign",
            parameters={@Parameter(value="userState",name="property")})
    @GeneratedValue(generator="foreignGenerator")
    private Long id;

    @OneToOne(mappedBy = "userState",fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    //账号是否过期
    private Boolean isAccountNonExpired;

    //账号是否锁定
    private Boolean isAccountNonLocked;

    //是否激活
    private Boolean isEnabled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
