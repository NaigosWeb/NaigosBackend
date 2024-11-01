package com.miaoyu.naigos.api.Manage.service;

import com.miaoyu.naigos.api.Manage.mapper.ManageUserMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManageUserService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private ManageUserMapper manageUserMapper;
    @Autowired
    private UserPermi userPermi;

    public Map<String, Object> getAllUserService(String uuid){
        userPermi.setPermissions(getUserPermiFromDB.getUserPermiByUuidService(uuid));
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        return new SuccessMap().standardSuccessMap(manageUserMapper.getAllUser());
    }
}
