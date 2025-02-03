package com.miaoyu.naigos.api.Blog.controller;

import com.miaoyu.naigos.api.Blog.entity.BlogCommentEntity;
import com.miaoyu.naigos.api.Blog.service.BlogCommentService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/blog/comment")
public class BlogCommentController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BlogCommentService blogCommentService;

    @PostMapping("/upload")
    public Map<String, Object> uploadBlogCommentControl(
            @RequestHeader("Authorization") String token,
            @RequestBody BlogCommentEntity request
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return blogCommentService.uploadBlogCommentService(payload.get("data").toString(), request);
    }
    @GetMapping("/get")
    public Map<String, Object> getBlogCommentControl(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(value = "access", required = false) String access,
            @RequestParam(value = "value", required = false) String value,
            @RequestParam("is_reply") boolean isReply
    ) {
        if (token != null) {
            Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
            if ((int) payload.get("code") == 1){
                return payload;
            }
            return blogCommentService.getBlogCommentByAuthorService(payload.get("data").toString());
        }
        return blogCommentService.getBlogCommentService(access, value, isReply);
    }
}
