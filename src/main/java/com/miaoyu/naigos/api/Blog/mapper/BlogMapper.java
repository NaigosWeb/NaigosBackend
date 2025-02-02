package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogBriefEntity;
import com.miaoyu.naigos.model.BlogModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    @Select("SELECT * FROM api_blog")
    List<BlogBriefEntity> selectAllBrief();
    @Select("SELECT * FROM api_blog WHERE blog_id = #{blog_id}")
    BlogModel selectBlogById(@Param("blog_id") String blogId);
    @Select("SELECT * FROM api_blog ORDER BY last_date DESC LIMIT #{limit}")
    List<BlogBriefEntity> selectNewBlogByLimit(@Param("limit") Integer limit);
    @Select("SELECT * FROM api_blog WHERE author = #{uuid}")
    List<BlogBriefEntity> selectBlogByUuid(@Param("uuid") String uuid);
}
