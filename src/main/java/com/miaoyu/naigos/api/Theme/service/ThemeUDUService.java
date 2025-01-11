package com.miaoyu.naigos.api.Theme.service;

import com.miaoyu.naigos.api.Theme.entity.ThemeClassifyBriefEntity;
import com.miaoyu.naigos.api.Theme.mapper.SubcategoryMapper;
import com.miaoyu.naigos.api.Theme.mapper.ThemeClassifyMapper;
import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.ThemeIdSubcategoryModel;
import com.miaoyu.naigos.model.ThemeModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.utils.GenerateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThemeUDUService {
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private ThemeClassifyMapper themeClassifyMapper;
    @Autowired
    private SubcategoryMapper subcategoryMapper;

    public Map<String, Object> uploadThemeService(String uuid, ThemeModel request){
        boolean b = getUserPermiFromDB.utilPermission(uuid, PermiConst.CREATOR);
        if (!b){
            return new ErrorMap().insufficientAccountPermissions();
        }
        request.setAuthor(uuid);
        GenerateUUID themeUUID = new GenerateUUID(new String[]{request.getAuthor(), request.getName()});
        request.setTheme_id(request.getTheme_id() + "_" + themeUUID.getUuid());
        ThemeModel theme = themeMapper.selectThemeById(request.getTheme_id());
        if (theme == null){
            boolean bt = themeMapper.insertTheme(request);
            if (bt){
                return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
            }
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
    public Map<String, Object> getAllClassifySubcategoryService(){
        List<ThemeClassifyBriefEntity> allThemeClassifyBrief = themeClassifyMapper.getAllThemeClassifyBrief();
        List<ThemeIdSubcategoryModel> allSubcategory = subcategoryMapper.selectAllSubcategory();
        Map<String, Object> classifyAndSubcategoryMap = new HashMap<>();
        classifyAndSubcategoryMap.put("classify_list", allThemeClassifyBrief);
        classifyAndSubcategoryMap.put("subcategory_list", allSubcategory);
        return new SuccessMap().standardSuccessMap(classifyAndSubcategoryMap);
    }
}
