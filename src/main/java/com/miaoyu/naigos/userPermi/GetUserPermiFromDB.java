package com.miaoyu.naigos.userPermi;

import com.miaoyu.naigos.api.Manage.mapper.ManageUserMapper;
import com.miaoyu.naigos.model.UserPermiModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserPermiFromDB {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private ManageUserMapper manageUserMapper;
    @Autowired
    private UserPermi userPermi;

    public int getUserPermiByUuidService(String uuid){
        UserPermiModel userPermi = getUserArchiveMapper.getUserPermiByUuid(uuid);
        if (userPermi == null){
            return 1;
        }
        return userPermi.getPermission();
    }
    public UserPermiModel getUserPermiRecordService(String uuid){
        return getUserArchiveMapper.getUserPermiByUuid(uuid);
    }
    public boolean utilPermission(String uuid, int targetPermission) {
        UserPermiModel userPermiDB = getUserPermiRecordService(uuid);
        if (userPermiDB == null){
            return manageUserMapper.insertUserPermi(uuid, PermiConst.USER + targetPermission);
        }
        boolean b = userPermi.matchPermission(userPermiDB.getPermission(), targetPermission);
        if (b){
            return true;
        }
        return manageUserMapper.updateUserPermi(uuid, userPermiDB.getPermission() + targetPermission);
    }
}
