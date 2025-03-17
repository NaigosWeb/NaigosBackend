package com.miaoyu.naigos.model;

public class UserExchangeUuidModel {
    private String web_uuid;
    private String group_uuid;
    private String code;
    private boolean is_code;
    private long expiration;

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

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getWeb_uuid() {
        return web_uuid;
    }

    public void setWeb_uuid(String web_uuid) {
        this.web_uuid = web_uuid;
    }

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }
}
