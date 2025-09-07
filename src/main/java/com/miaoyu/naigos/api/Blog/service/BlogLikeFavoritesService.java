// BlogLikeFavoritesService.java
package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.entity.BlogLikeEntity;
import com.miaoyu.naigos.api.Blog.mapper.BlogLikeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class BlogLikeFavoritesService {
    @Autowired
    private BlogLikeMapper blogLikeMapper;

    @Transactional
    public Map<String, Object> executeBlogLikeService(int user_id, boolean toggle_type, String blog_id){
        BlogLikeEntity request = new BlogLikeEntity();
        request.setUser_id(user_id);
        request.setBlog_id(blog_id);

        // 检查是否已经点赞
        boolean hasLiked = blogLikeMapper.checkLikeExists(user_id, blog_id) > 0;

        if (toggle_type) {
            if (hasLiked) {
                return new ErrorMap().uploadUpdateDeleteErrorMap(2); // 已经点赞过
            }

            boolean insertSuccess = blogLikeMapper.insertLikes(request);
            if (!insertSuccess) {
                return new ErrorMap().uploadUpdateDeleteErrorMap(0);
            }

            blogLikeMapper.updateLikeCountADD(blog_id);
            return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
        } else {
            if (!hasLiked) {
                return new ErrorMap().uploadUpdateDeleteErrorMap(3); // 尚未点赞
            }

            boolean deleteSuccess = blogLikeMapper.deleteLike(request);
            if (!deleteSuccess) {
                return new ErrorMap().uploadUpdateDeleteErrorMap(0);
            }

            blogLikeMapper.updateLikeCountREMOVE(blog_id);
            return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
        }
    }

    public Map<String, Object> getBlogLikesCount(String blog_id){
        int count = blogLikeMapper.selectLikeCountByBlogId(blog_id);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "success");
        result.put("like_count", count);
        return result;
    }
}