package com.miaoyu.naigos.api.Theme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestEntity {
    private String name;
    private List<String> tags;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonSetter
    public String getTagsJson() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this.tags);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @JsonProperty("tags")
    public void setTags(String tagsJson) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            this.tags = objectMapper.readValue(tagsJson, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
