package com.miaoyu.naigos.api.SgTheme.entity;

public class BaRecordClassifyBriefEntity {
    private String classify_id;
    private String router;
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

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }
}
