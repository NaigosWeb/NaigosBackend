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

    /**
     * 处理博客评论的上传请求。
     *
     * @param uuid    用户的唯一标识符
     * @param request 包含评论信息的实体对象
     * @return 成功或失败的信息map
     */
    public Map<String, Object> uploadBlogCommentService(String uuid, BlogCommentEntity request) {
        // 生成唯一的comment_id，使用当前时间戳、用户uuid和博客id组合而成
        String comment_id = System.currentTimeMillis() + "_" + uuid + "_" + request.getBlog_id();
        // 设置生成的comment_id到request对象中
        request.setComment_id(comment_id);
        // 设置作者uuid到request对象中
        request.setAuthor(uuid);
        // 设置上传时间为当前时间戳
        request.setUpload_time(System.currentTimeMillis());
        // 调用blogCommentMapper的insertComment方法插入评论数据
        boolean b = blogCommentMapper.insertComment(request);
        // 检查插入操作是否成功
        if (!b) {
            // 插入失败时返回错误信息map
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        // 插入成功时返回成功信息map
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }

    /**
     * 处理博客评论回复的上传请求。
     *
     * @param uuid    用户的唯一标识符
     * @param request 包含评论回复信息的实体对象
     * @return 成功或失败的信息map
     */
    public Map<String, Object> uploadBlogCommentReplyService(String uuid, BlogCommentReplyEntity request) {
        // 生成唯一的reply_id，使用当前时间戳、用户uuid和评论id组合而成
        String reply_id = System.currentTimeMillis() + "_" + uuid + "_" + request.getComment_id();
        // 设置生成的reply_id到request对象中
        request.setReply_id(reply_id);
        // 设置作者uuid到request对象中
        request.setAuthor(uuid);
        // 设置上传时间为当前时间戳
        request.setUpload_time(System.currentTimeMillis());
        // 调用blogCommentReplyMapper的insertReply方法插入评论回复数据
        boolean b = blogCommentReplyMapper.insertReply(request);
        // 检查插入操作是否成功
        if (!b) {
            // 插入失败时返回错误信息map
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        // 插入成功时返回成功信息map
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }

    /**
     * 根据作者获取所有评论。
     *
     * @param uuid 用户的唯一标识符
     * @return 包含评论列表的成功信息map
     */
    public Map<String, Object> getBlogCommentByAuthorService(String uuid) {
        // 查询指定作者的所有评论，并返回成功信息map
        return new SuccessMap().standardSuccessMap(blogCommentMapper.selectCommentByAuthor(uuid));
    }

    /**
     * 根据不同的条件获取博客评论及其回复。
     *
     * @param access   访问类型（如"comment_id", "blog_id"）
     * @param value    访问值
     * @param isReply  是否需要包含回复
     * @return 成功或失败的信息map
     */
    public Map<String, Object> getBlogCommentService(String access, String value, boolean isReply) {
        switch (access) {
            case "comment_id": {
                // 根据comment_id查询评论
                BlogCommentEntity comment = blogCommentMapper.selectCommentById(value);
                if (comment == null) {
                    // 如果评论不存在，返回资源不存在的错误信息map
                    return new ErrorMap().resourceNotExist();
                }
                if (isReply) {
                    // 如果需要包含回复，查询该评论的所有回复
                    Map<String, Object> result = new HashMap<>();
                    List<BlogCommentReplyEntity> replies = blogCommentReplyMapper.selectByCommentId(comment.getComment_id());
                    result.put("comment", comment);
                    result.put("replies", replies);
                    // 返回包含评论和回复的成功信息map
                    return new SuccessMap().standardSuccessMap(result);
                } else {
                    // 如果不需要包含回复，仅返回评论
                    return new SuccessMap().standardSuccessMap(comment);
                }
            }
            case "blog_id": {
                // 根据blog_id查询所有评论
                List<BlogCommentEntity> comments = blogCommentMapper.selectCommentByBlogId(value);
                if (isReply) {
                    // 如果需要包含回复，查询每个评论的所有回复
                    List<Map<String, Object>> list = new ArrayList<>();
                    for (BlogCommentEntity comment : comments) {
                        Map<String, Object> resultCommentAndReply = new HashMap<>();
                        List<BlogCommentReplyEntity> replies = blogCommentReplyMapper.selectByCommentId(comment.getComment_id());
                        resultCommentAndReply.put("comment", comment);
                        resultCommentAndReply.put("replies", replies);
                        list.add(resultCommentAndReply);
                    }
                    // 返回包含评论和回复的成功信息map
                    return new SuccessMap().standardSuccessMap(list);
                } else {
                    // 如果不需要包含回复，仅返回评论列表
                    return new SuccessMap().standardSuccessMap(comments);
                }
            }
            default: {
                // 如果访问类型无效，返回参数错误的错误信息map
                return new ErrorMap().errorMap("参数错误！");
            }
        }
    }
}
