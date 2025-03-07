package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogCommentReplyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogCommentReplyMapper {
    @Select("SELECT * FROM api_blog_comment_reply WHERE comment_id = #{comment_id} ORDER BY upload_time DESC")
    List<BlogCommentReplyEntity> selectByCommentId(@Param("comment_id") String comment_id);

    @Insert("INSERT INTO api_blog_comment_reply (reply_id, comment_id, author, content, upload_time) VALUES (#{reply_id}, #{comment_id}, #{author}, #{content}, #{upload_time})")
    boolean insertReply(BlogCommentReplyEntity blogCommentReplyEntity);
}
