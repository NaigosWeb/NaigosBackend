package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.entity.BlogCommentEntity;
import com.miaoyu.naigos.api.Blog.entity.BlogCommentReplyEntity;
import com.miaoyu.naigos.api.Blog.mapper.BlogCommentMapper;
import com.miaoyu.naigos.api.Blog.mapper.BlogCommentReplyMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogCommentService {
    @Autowired
    private BlogCommentMapper blogCommentMapper;
    @Autowired
    private BlogCommentReplyMapper blogCommentReplyMapper;

    public Map<String, Object> uploadBlogCommentService(String uuid, BlogCommentEntity request){
        String comment_id = System.currentTimeMillis() + "_" + uuid + "_" + request.getBlog_id();
        request.setComment_id(comment_id);
        request.setAuthor(uuid);
        request.setUpload_time(System.currentTimeMillis());
        boolean b = blogCommentMapper.insertComment(request);
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }
    public Map<String, Object> uploadBlogCommentReplyService(String uuid, BlogCommentReplyEntity request){
        String reply_id = System.currentTimeMillis() + "_" + uuid + "_" + request.getComment_id();
        request.setReply_id(reply_id);
        request.setAuthor(uuid);
        request.setUpload_time(System.currentTimeMillis());
        boolean b = blogCommentReplyMapper.insertReply(request);
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }
    public Map<String, Object> getBlogCommentByAuthorService(String uuid){
        return new SuccessMap().standardSuccessMap(blogCommentMapper.selectCommentByAuthor(uuid));
    }
    public Map<String, Object> getBlogCommentService(String access, String value, boolean isReply){
        switch (access) {
            case "comment_id": {
                BlogCommentEntity comment = blogCommentMapper.selectCommentById(value);
                if (comment == null) {
                    return new ErrorMap().resourceNotExist();
                }
                if (isReply) {
                    Map<String, Object> result = new HashMap<>();
                    List<BlogCommentReplyEntity> replies = blogCommentReplyMapper.selectByCommentId(comment.getComment_id());
                    result.put("comment", comment);
                    result.put("replies", replies);
                    return new SuccessMap().standardSuccessMap(result);
                } else {
                    return new SuccessMap().standardSuccessMap(comment);
                }
            }
            case "blog_id": {
                List<BlogCommentEntity> comments = blogCommentMapper.selectCommentByBlogId(value);
                if (isReply) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (BlogCommentEntity comment : comments) {
                        Map<String, Object> resultCommentAndReply = new HashMap<>();
                        List<BlogCommentReplyEntity> replies = blogCommentReplyMapper.selectByCommentId(comment.getComment_id());
                        resultCommentAndReply.put("comment", comment);
                        resultCommentAndReply.put("replies", replies);
                        list.add(resultCommentAndReply);
                    }
                    return new SuccessMap().standardSuccessMap(list);
                } else {
                    return new SuccessMap().standardSuccessMap(comments);
                }
            }
            default: {
                return new ErrorMap().errorMap("参数错误！");
            }
        }
    }
}
