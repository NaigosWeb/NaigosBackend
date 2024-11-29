package com.miaoyu.naigos.api.Theme.service;

import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.ThemeModel;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ThemeUDUService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private ThemeMapper themeMapper;

    public Map<String, Object> uploadThemeService(String uuid, ThemeModel request){
        boolean b = getUserPermiFromDB.utilPermission(uuid, PermiConst.CREATOR);
        if (!b){
            return new ErrorMap().insufficientAccountPermissions();
        }
        request.setAuthor(uuid);
        boolean bt = themeMapper.insertTheme(request);
        if (bt){
            return new SuccessMap().uploadOrUpdateSuccessMap(0);
        }
        return new ErrorMap().uploadOrUpdateErrorMap(0);
    }


}
