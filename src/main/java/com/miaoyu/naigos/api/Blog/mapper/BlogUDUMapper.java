package com.miaoyu.naigos.api.Blog.mapper;

import com.miaoyu.naigos.model.BlogModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BlogUDUMapper {
    @Insert("INSERT INTO api_blog (name, label, content, author, upload_date, last_date, attachment, classify_id, blog_id, cover_image, bg_image) VALUES (#{name}, #{label}, #{content}, #{author}, #{upload_date}, #{last_date}, #{attachment}, #{classify_id}, #{blog_id}, #{cover_image}, #{bg_image})")
    boolean insertBlog(BlogModel blogModel);

    @Update("UPDATE api_blog SET name = #{name}, label = #{label}, content = #{content}, last_date = #{last_date}, attachment = #{attachment}, classify_id = #{classify_id}, cover_image = #{cover_image}, bg_image = #{bg_image} WHERE blog_id = #{blog_id}")
    boolean updateBlog(BlogModel blogModel);

    @Delete("DELETE FROM api_blog WHERE blog_id = #{blog_id}")
    boolean deleteBlog(@Param("blog_id") String blogId);
}
