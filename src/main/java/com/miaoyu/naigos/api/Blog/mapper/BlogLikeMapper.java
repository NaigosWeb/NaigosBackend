// BlogLikeMapper.java
package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.api.Blog.entity.BlogLikeEntity;
import org.apache.ibatis.annotations.*;

public interface BlogLikeMapper {
    @Select("SELECT like_count FROM api_blog WHERE blog_id = #{blog_id}")
    int selectLikeCountByBlogId(@Param("blog_id") String blog_id);

    @Select("SELECT COUNT(*) FROM blog_likes WHERE user_id = #{user_id} AND blog_id = #{blog_id}")
    int checkLikeExists(@Param("user_id") int user_id, @Param("blog_id") String blog_id);

    @Insert("INSERT INTO blog_likes (user_id, blog_id) VALUES (#{user_id}, #{blog_id})")
    @Options(useGeneratedKeys = true, keyProperty = "like_id")
    boolean insertLikes(BlogLikeEntity blogLike);

    @Delete("DELETE FROM blog_likes WHERE user_id = #{user_id} AND blog_id = #{blog_id}")
    boolean deleteLike(BlogLikeEntity blogLike);

    @Update("UPDATE api_blog SET like_count = like_count + 1 WHERE blog_id = #{blog_id}")
    void updateLikeCountADD(@Param("blog_id") String blog_id);

    @Update("UPDATE api_blog SET like_count = like_count - 1 WHERE blog_id = #{blog_id} AND like_count > 0")
    void updateLikeCountREMOVE(@Param("blog_id") String blog_id);
}