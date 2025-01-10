package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.entity.BlogBriefEntity;
import com.miaoyu.naigos.api.Blog.mapper.BlogMapper;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public Map<String, Object> getAllBriefService() {
        return new SuccessMap().standardSuccessMap(blogMapper.selectAllBrief());
    }
    public Map<String, Object> getAllBlogEligibleBriefService(String label, String keyword, String uuid) {
        List<BlogBriefEntity> allBlogBrief = blogMapper.selectAllBrief();
        List<BlogBriefEntity> handleLabelBlogBrief = new ArrayList<>();
        if (label != null) {
            String[] labelArray = label.split("#");
            for (BlogBriefEntity blogBriefEntity : allBlogBrief) {
                String[] blogLabelArray = blogBriefEntity.getLabel().split("#");
                for (String blogLabelFor : blogLabelArray) {
                    for (String labelFor : labelArray) {
                        if (blogLabelFor.equals(labelFor)) {
                            handleLabelBlogBrief.add(blogBriefEntity);
                            break;
                        }
                    }
                }
            }
        } else {
            handleLabelBlogBrief = allBlogBrief;
        }
        List<BlogBriefEntity> handleKeywordBlogBrief = new ArrayList<>();
        if (keyword != null) {
            String[] keywordArray = keyword.split("#");
            for (BlogBriefEntity blogBriefEntity : handleLabelBlogBrief) {
                for (String keywordFor : keywordArray) {
                    if (blogBriefEntity.getName().contains(keywordFor)) {
                        handleKeywordBlogBrief.add(blogBriefEntity);
                    }
                }
            }
        } else {
            handleKeywordBlogBrief = handleLabelBlogBrief;
        }
        List<BlogBriefEntity> handleUuidBlogBrief = new ArrayList<>();
        if (uuid != null) {
            for (BlogBriefEntity blogBriefEntity : handleKeywordBlogBrief) {
                if (blogBriefEntity.getAuthor().equals(uuid)) {
                    handleUuidBlogBrief.add(blogBriefEntity);
                }
            }
        } else {
            handleUuidBlogBrief = handleKeywordBlogBrief;
        }
        return new SuccessMap().standardSuccessMap(handleUuidBlogBrief);
    }
}
