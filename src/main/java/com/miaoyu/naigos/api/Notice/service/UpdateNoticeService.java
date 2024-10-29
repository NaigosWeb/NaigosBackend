package com.miaoyu.naigos.api.Notice.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UpdateNoticeService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private UserPermi userPermi;
    public Map<String, Object> updateNoticeService(String uuid,
                                                   NaigosNoticeModel request
    ){
        userPermi.setPermissions(getUserPermiFromDB.getUserPermiByUuidService(uuid));
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)){
            return new ErrorMap().insufficientAccountPermissions();
        }

    }
}
