package com.miaoyu.naigos.ai.entity;

public class UserAIEntity {
    private String uuid;
    private String name;
    private String character_design;
    private int max_session;
    private Long expiration_session;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter_design() {
        return character_design;
    }

    public void setCharacter_design(String character_design) {
        this.character_design = character_design;
    }

    public int getMax_session() {
        return max_session;
    }

    public void setMax_session(int max_session) {
        this.max_session = max_session;
    }

    public long getExpiration_session() {
        return expiration_session;
    }

    public void setExpiration_session(Long expiration_session) {
        this.expiration_session = expiration_session;
    }
}
