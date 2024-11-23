package com.miaoyu.naigos.api.Theme.entity;

public class ThemeBriefEntity {
    private String name;
    private String header_image;
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

    public String getHeader_image() {
        return header_image;
    }

    public void setHeader_image(String header_image) {
        this.header_image = header_image;
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
