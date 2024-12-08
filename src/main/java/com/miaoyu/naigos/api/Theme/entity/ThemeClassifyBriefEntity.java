package com.miaoyu.naigos.api.Theme.entity;

public class ThemeClassifyBriefEntity {
    private String classify_id;
    private String cover_image;
    private String zhsg_name;
    private String router_name;
    private String classify_value;

    public String getClassify_value() {
        return classify_value;
    }

    public void setClassify_value(String classify_value) {
        this.classify_value = classify_value;
    }

    public String getRouter_name() {
        return router_name;
    }

    public void setRouter_name(String router_name) {
        this.router_name = router_name;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getZhsg_name() {
        return zhsg_name;
    }

    public void setZhsg_name(String zhsg_name) {
        this.zhsg_name = zhsg_name;
    }
}
