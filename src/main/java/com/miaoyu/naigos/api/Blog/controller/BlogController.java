package com.miaoyu.naigos.api.Blog.controller;

import com.miaoyu.naigos.api.Blog.service.BlogService;
import com.miaoyu.naigos.api.Blog.service.BlogUDUService;
import com.miaoyu.naigos.model.BlogModel;
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
    @Autowired
    private BlogUDUService blogUDUService;

    @GetMapping("/all_brief")
    public Map<String, Object> getAllBlogBriefControl() {
        return blogService.getAllBriefService();
    }
    @GetMapping("/eligible_brief")
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
    @GetMapping("/only")
    public Map<String, Object> getBlogOnlyControl(
            @RequestParam("blog_id") String blogId
    ) {
        return blogService.getBlogOnlyService(blogId);
    }
    @PostMapping("/upload")
    public Map<String, Object> uploadBlogControl (
            @RequestBody BlogModel requestBlog,
            @RequestHeader("Authorization") String token
            ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return blogUDUService.uploadBlogService(requestBlog, payload.get("data").toString());
    }
    @DeleteMapping("/delete")
    public Map<String, Object> deleteBlogControl (
            @RequestParam("blog_id") String blogId,
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return blogUDUService.deleteBlogService(blogId, payload.get("data").toString());
    }
    @PutMapping("/update")
    public Map<String, Object> updateBlogControl (
            @RequestBody BlogModel requestBlog,
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return blogUDUService.updateBlogService(requestBlog, payload.get("data").toString());
    }
}
