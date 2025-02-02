package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.entity.BlogBriefEntity;
import com.miaoyu.naigos.api.Blog.mapper.BlogMapper;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogInformationService {
    @Autowired
    private BlogMapper blogMapper;

    public Map<String, Object> getBlogInformationUsercardService(String uuid) {
        List<BlogBriefEntity> briefBlogs = blogMapper.selectBlogByUuid(uuid);
        int blogAmount = briefBlogs.size();
        Map<String, Object> result = new HashMap<>();
        result.put("blog_amount", blogAmount);
        return new SuccessMap().standardSuccessMap(result);
    }
}
