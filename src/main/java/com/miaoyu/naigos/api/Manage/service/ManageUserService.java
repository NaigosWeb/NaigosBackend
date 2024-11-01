package com.miaoyu.naigos.api.Manage.service;

import com.miaoyu.naigos.api.Manage.mapper.ManageUserMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserPermiModel;
import com.miaoyu.naigos.user.service.EditArchiveService;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.SortedMap;

@Service
public class ManageUserService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private EditArchiveService editArchiveService;
    @Autowired
    private GetUserArchiveService getUserArchiveService;
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

    public Map<String, Object> editUserService(String uuid, UserArchiveModel request){
        int modifiedUserPermission = getUserPermiFromDB.getUserPermiByUuidService(request.getGroup_real_user_id());;
        UserArchiveModel modifiedUser = getUserArchiveService.getUserArchive(2, request.getGroup_real_user_id());
        int userPermission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        userPermi.setPermissions(userPermission);
        if (modifiedUser == null) {
            return new ErrorMap().noSuchArchive();
        }
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        if ((modifiedUserPermission & PermiConst.ADMIN) != 0){
            return new ErrorMap().insufficientAccountPermissions();
        }
        if ((userPermission <= PermiConst.MANAGER && PermiConst.MANAGER <= modifiedUserPermission) || (userPermi.hasPermission(PermiConst.ADMIN) && PermiConst.ADMIN <= modifiedUserPermission)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        return editArchiveService.editArchiveService(request.getGroup_real_user_id(), request);
    }
    public Map<String, Object> changePermiService(String uuid, String modifiedUuid, int permission){
        if (permission <= 0){
            return new ErrorMap().errorMap("未知的权限值");
        }
        int userPermission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        int modifiedPermission = getUserPermiFromDB.getUserPermiByUuidService(modifiedUuid);
        userPermi.setPermissions(userPermission);
        if (!userPermi.hasPermission(PermiConst.MANAGER) && !userPermi.hasPermission(PermiConst.ADMIN)) {
            return new ErrorMap().insufficientAccountPermissions();
        }
        if (userPermi.hasPermission(PermiConst.MANAGER)) {
            if (userPermi.hasPermission(PermiConst.ADMIN)){
                if (PermiConst.ADMIN <= modifiedPermission || PermiConst.ADMIN <= permission){
                    return new ErrorMap().insufficientAccountPermissions();
                }
            } else {
                if (PermiConst.MANAGER <= modifiedPermission || PermiConst.MANAGER <= permission){
                    return new ErrorMap().insufficientAccountPermissions();
                }
            }
        }
        UserPermiModel modifiedPermi = manageUserMapper.getUserPermiByUuid(modifiedUuid);
        if (modifiedPermi == null){
            boolean b = manageUserMapper.insertUserPermi(modifiedUuid, permission);
            if (b){
                return new SuccessMap().standardSuccessMap("修改权限成功！");
            }else {
                return new ErrorMap().errorMap("修改权限失败！");
            }
        }
        boolean b = manageUserMapper.updateUserPermi(modifiedUuid, permission);
        if (b){
            return new SuccessMap().standardSuccessMap("修改权限成功！");
        }else {
            return new ErrorMap().errorMap("修改权限失败！");
        }
    }
}
