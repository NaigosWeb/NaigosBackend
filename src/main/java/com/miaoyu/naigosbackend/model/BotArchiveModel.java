package com.miaoyu.naigosbackend.model;

public class BotArchiveModel {
    private long bot_appid;
    private String password;
    private int safe_level;
    private String token;

    public long getBot_appid() {
        return bot_appid;
    }

    public void setBot_appid(long bot_appid) {
        this.bot_appid = bot_appid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSafe_level() {
        return safe_level;
    }

    public void setSafe_level(int safe_level) {
        this.safe_level = safe_level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
