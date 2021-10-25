package com.fzc.fzcuser.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.mongodb.core.index.TextIndexed;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * user_info
 * @author 
 */
@TableName("user_info")
public class UserInfo implements Serializable {


    /**
     * 主键，用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TextIndexed(weight = 1)
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    @TextIndexed(weight = 1)
    private String email;

    private String qq;

    /**
     * 角色(admin or normal user)
     */
    private String role;

    /**
     * 是否激活(0是未激活，1是已激活)
     */
    private Integer isActive;

    /**
     * 账户创建日期
     */
    private String createDate;


    private Integer status;


    private String currentAuthority;


    private String country;


    private Integer age;


    private String type;


    private String name;


    private String icon;


    @TextIndexed(weight = 1)
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentAuthority() {
        return currentAuthority;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public void setPassword(String userPassword) {
        this.password = userPassword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public UserInfo(String username, String userPassword, String sex, String role) {
        this.username = username;
        this.password = userPassword;
        this.sex = sex;
        this.role = role;
        int i = 1;
        setIsActive(i);
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        setCreateDate(sdf.format(date));
    }

    public UserInfo(Integer userId, String username, String userPassword, String sex, String birthday, String email, String qq, String role, Integer isActive, String createDate, Integer status, String currentAuthority, String country, Integer age, String type, String name, String icon) {
        this.userId = userId;
        this.username = username;
        this.password = userPassword;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.qq = qq;
        this.role = role;
        this.isActive = isActive;
        this.createDate = createDate;
        this.status = status;
        this.currentAuthority = currentAuthority;
        this.country = country;
        this.age = age;
        this.type = type;
        this.name = name;
        this.icon = icon;
    }

    public UserInfo() {
    }

    public UserInfo(String username, String userPassword, String sex, String birthday, String email, String qq, String role, Integer isActive) {
        this.username = username;
        this.password = userPassword;
        this.sex = sex;
        this.birthday = birthday;
        this.email = email;
        this.qq = qq;
        this.role = role;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(username).append('\'');
        sb.append(", userPassword='").append(password).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", birthday='").append(birthday).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", qq='").append(qq).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", createDate='").append(createDate).append('\'');
        sb.append('}');
        return sb.toString();
    }




}