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

@Service
public class DeleteNoticeService {
    @Autowired
    private NaigosNoticeMapper naigosNoticeMapper;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private UserPermi userPermi;

    public Map<String, Object> deleteNoticeService(String uuid, String noticeId){
        NaigosNoticeModel noticeDetail = naigosNoticeMapper.getNoticeById(noticeId);
        int userPermission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        userPermi.setPermissions(userPermission);
        if (noticeDetail == null){
            return new ErrorMap().errorMap("公告不存在！");
        }
        if (userPermi.hasPermission(PermiConst.ADMIN)){
            boolean b = naigosNoticeMapper.deleteNoticeAdmin(noticeId);
            if (b){
                return new SuccessMap().standardSuccessMap("超级管理员删除公告成功！");
            }
            return new ErrorMap().errorMap("超级管理员删除公告失败！");
        } else if (userPermi.hasPermission(PermiConst.MANAGER)) {
            boolean b = naigosNoticeMapper.deleteNotice(noticeId, uuid);
            if (b){
                return new SuccessMap().standardSuccessMap("删除公告成功！");
            }
            return new ErrorMap().errorMap("删除公告失败！");
        }
        return new ErrorMap().errorMap("您既不是该公告的发布者也不是超级管理员！");
    }
}
