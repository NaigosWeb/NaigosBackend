package com.miaoyu.naigos.model;

public class UserServiceScopeModel {
    private String uuid;
    private boolean naigos;
    private boolean theme;
    private boolean beautify;
    private boolean blue_archive;
    private boolean naigos_apply;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isNaigos() {
        return naigos;
    }

    public void setNaigos(boolean naigos) {
        this.naigos = naigos;
    }

    public boolean isTheme() {
        return theme;
    }

    public void setTheme(boolean theme) {
        this.theme = theme;
    }

    public boolean isBeautify() {
        return beautify;
    }

    public void setBeautify(boolean beautify) {
        this.beautify = beautify;
    }

    public boolean isBlue_archive() {
        return blue_archive;
    }

    public void setBlue_archive(boolean blue_archive) {
        this.blue_archive = blue_archive;
    }

    public boolean isNaigos_apply() {
        return naigos_apply;
    }

    public void setNaigos_apply(boolean naigos_apply) {
        this.naigos_apply = naigos_apply;
    }
}
