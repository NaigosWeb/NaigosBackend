package com.miaoyu.naigos.api.Blog.controller;

import com.miaoyu.naigos.api.Blog.service.BlogService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BlogService blogService;

    @GetMapping("/all_brief")
    public Map<String, Object> getAllBlogBriefControl() {
        return blogService.getAllBriefService();
    }
    @GetMapping("/all_eligible_brief")
    public Map<String, Object> getAllBlogEligibleBriefControl(
            @RequestParam(value = "label", required = false) String label,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestHeader(value = "Authorization", required = false) String token
            ) {
        String uuid = null;
        if (token != null) {
            Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
            if ((int) payload.get("code") == 1){
                return payload;
            }
            uuid = payload.get("data").toString();
        }
        return blogService.getAllBlogEligibleBriefService(label, keyword, uuid);
    }
}
