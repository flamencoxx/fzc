package com.fzc.fzcusershiro.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.io.Serializable;
import java.util.Date;

/**
 * @author flamenco.xxx
 * @date 2021/12/14 15:04
 */
@TableName("user_shiro")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TextIndexed(weight = 1)
    private String username;

    private String password;

    @TextIndexed(weight = 1)
    private String email;

    @TextIndexed(weight = 1)
    private String mobile;

    private String status;

    private Date creatTime;

    private Date modifyTime;

    private Date lastLoginTime;

    private String sex;

    private String salt;

    private int encodeTimes;

    private String avatar;

    private String description;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status='" + status + '\'' +
                ", creatTime=" + creatTime +
                ", modifyTime=" + modifyTime +
                ", lastLoginTime=" + lastLoginTime +
                ", sex='" + sex + '\'' +
                ", salt='" + salt + '\'' +
                ", encodeTimes=" + encodeTimes +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getEncodeTimes() {
        return encodeTimes;
    }

    public void setEncodeTimes(int encodeTimes) {
        this.encodeTimes = encodeTimes;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
