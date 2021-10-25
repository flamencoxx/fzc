package com.fzc.fzcuser.model;

import java.util.Objects;

/**
 * @author Flamenco.xxx
 * @date 2021/9/17 9:10
 */
public class UserRegister {
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String confirm;

    public UserRegister(String username, String password, String email, String mobile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }

    public UserRegister() {
    }


    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserRegister{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(mobile).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegister that = (UserRegister) o;
        return getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getMobile(), that.getMobile()) && getConfirm().equals(that.getConfirm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getEmail(), getMobile(), getConfirm());
    }
}
