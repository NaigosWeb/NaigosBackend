package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogBriefEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {
    @Select("SELECT * FROM api_blog")
    List<BlogBriefEntity> selectAllBrief();
}
