package com.miaoyu.naigos.api.Blog.controller;

import com.miaoyu.naigos.api.Blog.service.BlogInformationService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/blog/information")
public class BlogInformationController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BlogInformationService blogInformationService;

    @GetMapping("/usercard")
    public Map<String, Object> getBlogInformationUsercardControl(
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return blogInformationService.getBlogInformationUsercardService(payload.get("data").toString());
    }
}
