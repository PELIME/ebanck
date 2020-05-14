package com.pelime.xtools.sysmodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //头像url
    private String iconPath;
    //用户账号名，用户唯一标识，登陆账号
    private String accountName;
    //用户登陆密码
    private String password;
    //身份证上的姓名
    private String realName;
    //身份证号
    private String idCardNo;
    private String phone;
    private LocalDate birthday;
    private Timestamp createTime;
    private String sex;
    private String address;
    private int age;
    //会员积分
    private Long memberIntegral;
    //会员余额
    private Float balance;
    //会员状态 挂失、停用、正常
    private String state;
    private String email;
    //账号备注
    private String remark;
    //会员信息 多个会员由“，”分割
    private String memberGrade;
    //保留扩展字段
    private String reserved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Long memberIntegral) {
        this.memberIntegral = memberIntegral;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", iconPath='" + iconPath + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", createTime=" + createTime +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", memberIntegral=" + memberIntegral +
                ", balance=" + balance +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", remark='" + remark + '\'' +
                ", memberGrade='" + memberGrade + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}
