package com.miaoyu.naigos.blueArchive.service;

import com.miaoyu.naigos.blueArchive.mapper.BaStudentMapper;
import com.miaoyu.naigos.blueArchive.model.BaStudentModel;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaStudentService {
    @Autowired
    private BaStudentMapper baStudentMapper;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;

    /**获取全部student实体*/
    public Map<String, Object> getBaAllStudentsService() {
        return new SuccessMap().standardSuccessMap(baStudentMapper.selectAll());
    }

    /**根据school_id获取全部符合条件的student实体*/
    public Map<String, Object> getBaStudentsBySchoolIdService(String schoolId) {
        return new SuccessMap().standardSuccessMap(baStudentMapper.selectListBySchoolId(schoolId));
    }

    /**根据club_id获取全部符合条件的student实体*/
    public Map<String, Object> getBaStudentsByClubIdService(String clubId) {
        return new SuccessMap().standardSuccessMap(baStudentMapper.selectListByClubId(clubId));
    }

    /**根据student_id获取唯一符合条件的student实体*/
    public Map<String, Object> getBaStudentByIdService(String studentId) {
        BaStudentModel student = baStudentMapper.selectById(studentId);
        if (student == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(student);
    }

    /**上传student实体*/
    public Map<String, Object> upBaStudentService(String uuid, String upType, BaStudentModel request) {
        int permission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        if (permission < PermiConst.MANAGER) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        boolean b;
        switch (upType) {
            case "upload": {
                b = baStudentMapper.insert(request);
                break;
            }
            case "update": {
                b = baStudentMapper.update(request);
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

    /**根据student_id删除student实体*/
    public Map<String, Object> deleteBaStudentService(String uuid, String studentId) {
        int permission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        if (permission < PermiConst.MANAGER) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        boolean b = baStudentMapper.delete(studentId);
        if (!b) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(2);
        }
        return new SuccessMap().uploadUpdateDeleteSuccessMap(2);
    }
}
