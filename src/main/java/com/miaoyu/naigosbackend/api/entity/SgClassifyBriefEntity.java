package com.miaoyu.naigosbackend.api.entity;

public class SgClassifyBriefEntity {
    private String classify_id;
    private String cover_image;
    private String zhsg_name;

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
