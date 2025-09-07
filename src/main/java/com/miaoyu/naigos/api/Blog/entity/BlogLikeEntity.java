// BlogLikeEntity.java
package com.miaoyu.naigos.api.Blog.entity;

public class BlogLikeEntity {
    private String blog_id;
    private int user_id;
    private int like_id;

    public String getBlog_id() {
        return blog_id;
    }
    public void setBlog_id(String blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLike_id() {
        return like_id;
    }
    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }
}