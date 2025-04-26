package com.miaoyu.naigos.blueArchive.service;

import com.miaoyu.naigos.blueArchive.mapper.BaSchoolMapper;
import com.miaoyu.naigos.blueArchive.model.BaSchoolModel;
import com.miaoyu.naigos.blueArchive.model.BaStudentModel;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaSchoolService {
    @Autowired
    private BaSchoolMapper baSchoolMapper;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;

    /**获取全部school简要信息*/
    public Map<String, Object> getBaAllSchoolService() {
        return new SuccessMap().standardSuccessMap(baSchoolMapper.selectAll());
    }

    /**根据school_id获得符合的school实体*/
    public Map<String, Object> getBaSchoolByIdService(String schoolId) {
        BaSchoolModel school = baSchoolMapper.selectById(schoolId);
        if (school == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(school);
    }

    /**上传school参数*/
    public Map<String, Object> upBaSchoolService(String uuid, String upType, BaSchoolModel request) {
        int permission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        if (permission < PermiConst.MANAGER) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        boolean b;
        switch (upType) {
            case "upload": {
                b = baSchoolMapper.insert(request);
                break;
            }
            case "update": {
                b = baSchoolMapper.update(request);
                break;
            }
            default: {
                return new ErrorMap().uploadUpdateDeleteErrorMap(0);
            }
        }
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
    }
}
