package com.miaoyu.naigos.api.NaigosNotice.service;

import com.miaoyu.naigos.api.NaigosNotice.mapper.NaigosNoticeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class UpdateNoticeService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private NaigosNoticeMapper naigosNoticeMapper;
    @Autowired
    private UserPermi userPermi;

    public Map<String, Object> updateNoticeService(String uuid, NaigosNoticeModel request){
        NaigosNoticeModel noticeDetail = naigosNoticeMapper.getNoticeById(request.getNotice_id());
        userPermi.setPermissions(getUserPermiFromDB.getUserPermiByUuidService(uuid));
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)){
            return new ErrorMap().insufficientAccountPermissions();
        }
        if(noticeDetail == null){
            return new ErrorMap().errorMap("公告不存在！");
        }
        if (!userPermi.hasPermission(PermiConst.ADMIN)
                && !Objects.equals(uuid, noticeDetail.getAuthor())) {
            return new ErrorMap().errorMap("您既不是该公告的发布者也不是超级管理员！");
        }
        boolean b = naigosNoticeMapper.updateNotice(
                request.getNotice_id(),
                request.getTitle(),
                request.getContent(),
                request.getAttachment(),
                request.getImage(),
                System.currentTimeMillis());
        if (b){
            return new SuccessMap().standardSuccessMap("公告修改成功！");
        } else {
            return new ErrorMap().errorMap("公告修改失败！");
        }
    }
}
