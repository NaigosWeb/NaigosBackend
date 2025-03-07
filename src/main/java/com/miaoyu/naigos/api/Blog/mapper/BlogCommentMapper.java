package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogCommentEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogCommentMapper {
    @Select("SELECT * FROM api_blog_comment WHERE comment_id = #{comment_id}")
    BlogCommentEntity selectCommentById(@Param("comment_id") String comment_id);
    @Select("SELECT * FROM api_blog_comment WHERE blog_id = #{blog_id} ORDER BY upload_time DESC")
    List<BlogCommentEntity> selectCommentByBlogId(@Param("blog_id") String blog_id);
    @Select("SELECT * FROM api_blog_comment WHERE author = #{author} ORDER BY upload_time DESC")
    List<BlogCommentEntity> selectCommentByAuthor(@Param("author") String author);

    @Insert("INSERT INTO api_blog_comment (comment_id, blog_id, author, content, upload_time) VALUES (#{comment_id}, #{blog_id}, #{author}, #{content}, #{upload_time})")
    boolean insertComment(BlogCommentEntity blogComment);
    @Delete("DELETE FROM api_blog_comment WHERE comment_id = #{comment_id}")
    boolean deleteCommentById(@Param("comment_id") String comment_id);
}
