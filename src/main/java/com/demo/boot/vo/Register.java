package com.demo.boot.vo;

import com.demo.boot.utils.StringUtils;

/**
 * 注册使用Vo
 */
public class Register {

    private String displayName;

    private String userName;

    private String password;

    private String rePassword;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isEmpty() {
        return StringUtils.isBlank(displayName)
                || StringUtils.isBlank(userName)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(rePassword);
    }
}
