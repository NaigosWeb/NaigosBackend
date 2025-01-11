package com.miaoyu.naigos.api.Blog.service;

import com.miaoyu.naigos.api.Blog.mapper.BlogMapper;
import com.miaoyu.naigos.api.Blog.mapper.BlogUDUMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.BlogModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.utils.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BlogUDUService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private BlogUDUMapper blogUDUMapper;
    @Autowired
    private BlogMapper blogMapper;

    public Map<String, Object> uploadBlogService(BlogModel requestBlog, String uuid) {
        boolean b = getUserPermiFromDB.utilPermission(uuid, PermiConst.CREATOR);
        if (!b) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        requestBlog.setAuthor(uuid);
        long timestamp = System.currentTimeMillis();
        requestBlog.setUpload_date(timestamp);
        requestBlog.setLast_date(timestamp);
        GenerateUUID generateBlogId = new GenerateUUID(new String[]{timestamp + uuid, requestBlog.getName()});
        requestBlog.setBlog_id(requestBlog.getClassify_id() != null? requestBlog.getClassify_id(): "NORMAL" + "_" + generateBlogId.getUuid());
        boolean insertBlog = blogUDUMapper.insertBlog(requestBlog);
        if (!insertBlog) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }

    public Map<String, Object> deleteBlogService(String blogId, String uuid) {
        int userPermission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        BlogModel blog = blogMapper.selectBlogById(blogId);
        if (userPermission < PermiConst.MANAGER && !blog.getAuthor().equals(uuid)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        boolean b = blogUDUMapper.deleteBlog(blogId);
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(2);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(2);
    }
    public Map<String, Object> updateBlogService(BlogModel requestBlog, String uuid) {
        if (!requestBlog.getAuthor().equals(uuid)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        requestBlog.setLast_date(System.currentTimeMillis());
        boolean b = blogUDUMapper.updateBlog(requestBlog);
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(1);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(1);
    }
}
