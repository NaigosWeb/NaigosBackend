package com.miaoyu.naigos.userPermi;

import com.miaoyu.naigos.model.UserPermiModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserPermiFromDB {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

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
}
