package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogCommentReplyEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogCommentReplyMapper {
    @Select("SELECT * FROM api_blog_comment_reply WHERE comment_id = #{comment_id}")
    List<BlogCommentReplyEntity> selectByCommentId(@Param("comment_id") String comment_id);
}
