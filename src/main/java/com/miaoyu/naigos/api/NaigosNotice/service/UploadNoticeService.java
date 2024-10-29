package com.miaoyu.naigos.api.NaigosNotice.service;

import com.miaoyu.naigos.api.NaigosNotice.mapper.NaigosNoticeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import com.miaoyu.naigos.utils.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UploadNoticeService {
    @Autowired
    private NaigosNoticeMapper naigosNoticeMapper;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private UserPermi userPermi;
    public Map<String, Object> uploadNoticeService(String uuid,
                                                   NaigosNoticeModel request
    ){
        userPermi.setPermissions(getUserPermiFromDB.getUserPermiByUuidService(uuid));
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)){
            return new ErrorMap().insufficientAccountPermissions();
        }
        long timestamp = System.currentTimeMillis();
        String[] args = new String[]{uuid, request.getTitle()};
        boolean b = naigosNoticeMapper.uploadNotice(
                new GenerateUUID(args).getUuid().toString(),
                request.getTitle(),
                request.getContent(),
                uuid,
                request.getAttachment(),
                request.getImage(),
                timestamp,
                timestamp);
        if (b) {
            return new SuccessMap().standardSuccessMap("公告发布成功！");
        }
        return new ErrorMap().errorMap("公告发布失败！");
    }
}
