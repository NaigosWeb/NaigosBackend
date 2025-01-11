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
}
