/*
* 用户的密码模型*/
package com.miaoyu.naigosbackend.model;

public class UserPasswordModel {
    private String uuid;
    private String password;
    private String code;
    private boolean is_code;
    private long expiration_date;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isIs_code() {
        return is_code;
    }

    public void setIs_code(boolean is_code) {
        this.is_code = is_code;
    }

    public long getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(long expiration_date) {
        this.expiration_date = expiration_date;
    }
}
