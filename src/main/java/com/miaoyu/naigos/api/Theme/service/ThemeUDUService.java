package com.miaoyu.naigos.api.Theme.service;

import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.ThemeModel;
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
            return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
        }
        return new ErrorMap().uploadUpdateDeleteErrorMap(0);
    }

    public Map<String, Object> deleteThemeService(String uuid, String theme_id) {
        ThemeModel themeDB = themeMapper.selectThemeById(theme_id);
        if (themeDB == null){
            return new ErrorMap().resourceNotExist();
        }
        int permission = getUserPermiFromDB.getUserPermiByUuidService(uuid);
        if (PermiConst.MANAGER <= permission) {
            boolean b = themeMapper.deleteTheme(theme_id);
            if (b){
                return new SuccessMap().uploadUpdateDeleteSuccessMap(2);
            }
            return new ErrorMap().uploadUpdateDeleteErrorMap(2);
        }
        if (themeDB.getAuthor().equals(uuid)){
            boolean b = themeMapper.deleteTheme(theme_id);
            if (b){
                return new SuccessMap().uploadUpdateDeleteSuccessMap(2);
            }
            return new ErrorMap().uploadUpdateDeleteErrorMap(2);
        }
        return new ErrorMap().insufficientAccountPermissions();
    }
    public Map<String, Object> updateThemeService(String uuid, ThemeModel request){
        if (request.getAuthor().equals(uuid)){
            boolean b = themeMapper.updateTheme(request);
            if (b){
                return new SuccessMap().uploadUpdateDeleteSuccessMap(1);
            }
            return new ErrorMap().uploadUpdateDeleteErrorMap(1);
        }
        return new ErrorMap().insufficientAccountPermissions();
    }

}
