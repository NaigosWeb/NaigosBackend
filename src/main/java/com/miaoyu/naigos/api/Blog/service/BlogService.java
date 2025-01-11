package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.entity.BlogBriefEntity;
import com.miaoyu.naigos.api.Blog.mapper.BlogMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public Map<String, Object> getAllBriefService() {
        return new SuccessMap().standardSuccessMap(blogMapper.selectAllBrief());
    }
    public Map<String, Object> getAllBlogEligibleBriefService(String label, String keyword, String uuid) {
        List<BlogBriefEntity> allBlogBrief = blogMapper.selectAllBrief();
        List<BlogBriefEntity> filteredBlogBrief;
        if (keyword != null && !keyword.isEmpty()) {
            String[] keywordArray = keyword.split("#");
            filteredBlogBrief = allBlogBrief.stream()
                    .filter(blog -> Arrays.stream(keywordArray).anyMatch(blog.getName()::contains))
                    .collect(Collectors.toList());
        } else {
            filteredBlogBrief = allBlogBrief;
        }
        if (uuid != null && !uuid.isEmpty()) {
            filteredBlogBrief = filteredBlogBrief.stream()
                    .filter(blog -> blog.getAuthor().equals(uuid))
                    .collect(Collectors.toList());
        }
        return new SuccessMap().standardSuccessMap(filteredBlogBrief);
    }
    public Map<String, Object> getBlogOnlyService(String blogId) {
        BlogModel blog = blogMapper.selectBlogById(blogId);
        if (blog == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(blog);
    }
}
