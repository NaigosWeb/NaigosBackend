package com.miaoyu.naigos.api.Theme.entity;

import java.util.List;

public class TestRequestEntity {
    private String name;
    private List<String> tagsArray;

    public List<String> getTagsArray() {
        return tagsArray;
    }

    public void setTagsArray(List<String> tagsArray) {
        this.tagsArray = tagsArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
