// BlogLikeFavoritesController.java
package com.miaoyu.naigos.api.Blog.controller;

import com.miaoyu.naigos.api.Blog.entity.BlogLikeEntity;
import com.miaoyu.naigos.api.Blog.service.BlogLikeFavoritesService;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/blog/BLF")
public class BlogLikeFavoritesController {
    @Autowired
    private NeedTokenUtil needTokenUtil;

    @Autowired
    private BlogLikeFavoritesService blogLikeFavoritesService;

    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

    @PostMapping("/addBlogLike")
    public Map<String, Object> addBlogLikeControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("toggle_type") boolean toggle_type,
            @RequestParam("blog_id") String blog_id
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
//        int userId = Integer.parseInt(payload.get("userId").toString());
        String uuid = payload.get("data").toString();
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        if (userArchive == null) {
            return new ErrorMap().noSuchArchive();
        }
        int userId = userArchive.getId();
        return blogLikeFavoritesService.executeBlogLikeService(userId, toggle_type, blog_id);
    }

    @GetMapping("/getBlogLikeCount")
    public Map<String, Object> getBlogLikeCount(@RequestParam("blog_id") String blog_id) {
        return blogLikeFavoritesService.getBlogLikesCount(blog_id);
    }
}