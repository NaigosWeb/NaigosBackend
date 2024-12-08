package com.miaoyu.naigos.model;

public class ThemeModel {
    private String name;
    private String url;
    private String introduce;
    private String header_image;
    private String detail_html;
    private int cost;
    private String eject_image;
    private String theme_id;
    private String classify_id;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHeader_image() {
        return header_image;
    }

    public void setHeader_image(String header_image) {
        this.header_image = header_image;
    }

    public String getDetail_html() {
        return detail_html;
    }

    public void setDetail_html(String detail_html) {
        this.detail_html = detail_html;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getEject_image() {
        return eject_image;
    }

    public void setEject_image(String eject_image) {
        this.eject_image = eject_image;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }
}
