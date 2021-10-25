package com.fzc.fzcuser.model;

/**
 * @author Flamenco.xxx
 * @date 2021/9/16 18:14
 */
public class UserPayload {
    private String userName;
    private String password;
    private boolean autoLogin;
    private String type;

    public UserPayload(String userName, String password, boolean autoLogin, String type) {
        this.userName = userName;
        this.password = password;
        this.autoLogin = autoLogin;
        this.type = type;
    }



    public UserPayload() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserPayload{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", autoLogin=").append(autoLogin);
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
