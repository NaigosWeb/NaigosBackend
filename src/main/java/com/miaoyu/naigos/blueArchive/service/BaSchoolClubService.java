package com.miaoyu.naigos.blueArchive.service;

import com.miaoyu.naigos.blueArchive.mapper.BaSchoolClubMapper;
import com.miaoyu.naigos.blueArchive.model.BaSchoolClubModel;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaSchoolClubService {
    @Autowired
    private BaSchoolClubMapper baSchoolClubMapper;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;

    /**获取全部club实体*/
    public Map<String, Object> getBaAllClubService() {
        return new SuccessMap().standardSuccessMap(baSchoolClubMapper.selectAll());
    }

    /**根据school_id获取全部符合的club实体*/
    public Map<String, Object> getBaClubsBySchoolIdService(String schoolId) {
        return new SuccessMap().standardSuccessMap(baSchoolClubMapper.selectListBySchoolId(schoolId));
    }

    /**根据club_id获取唯一的club实体*/
    public Map<String, Object> getBaClubByIdService(String clubId) {
        BaSchoolClubModel schoolClub = baSchoolClubMapper.selectById(clubId);
        if (schoolClub == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(schoolClub);
    }

    /**上传club参数*/
    public Map<String, Object> upBaClubService(String uuid, String upType, BaSchoolClubModel request) {
        int permission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        if (permission < PermiConst.MANAGER) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        boolean b;
        switch (upType) {
            case "upload": {
                b = baSchoolClubMapper.insert(request);
                break;
            }
            case "update": {
                b = baSchoolClubMapper.update(request);
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
