package com.miaoyu.naigos.api.Theme.service;

import com.miaoyu.naigos.api.Manage.mapper.ManageUserMapper;
import com.miaoyu.naigos.api.SgTheme.service.GetSgService;
import com.miaoyu.naigos.api.Theme.entity.ThemeBriefEntity;
import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.ThemeModel;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserPermiModel;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ThemeService {
    @Autowired private ThemeMapper themeMapper;
    @Autowired private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired private UserPermi userPermi;
    @Autowired private GetUserArchiveService getUserArchiveService;
    @Autowired private ManageUserMapper manageUserMapper;
    @Autowired private GetSgService getSgService;

    public Map<String, Object> getAllThemeBriefService(){
        return new SuccessMap().standardSuccessMap(themeMapper.selectAllThemeBrief());
    }
    public Map<String, Object> getAllThemeBriefByUuid(String uuid){
        List<ThemeBriefEntity> allThemeBriefList = themeMapper.selectAllThemeBrief();
        List<ThemeBriefEntity> utilThemeBriefList = new ArrayList<>();
        for (ThemeBriefEntity themeBrief : allThemeBriefList) {
            if (themeBrief.getAuthor().equals(uuid)) {
                utilThemeBriefList.add(themeBrief);
            }
        }
        return new SuccessMap().standardSuccessMap(utilThemeBriefList);
    }
    public Map<String, Object> getAllThemeEligibleBriefService(String classifyId){
        if (classifyId.isEmpty()){
            return new SuccessMap().standardSuccessMap(themeMapper.selectAllNullThemeBrief());
        }
        if (Objects.equals(classifyId, "sogou_input_theme")){
            return new SuccessMap().standardSuccessMap(getSgService.findAllBriefSg().get("data"));
        }
        return new SuccessMap().standardSuccessMap(themeMapper.selectAllEligibleThemeBrief(classifyId));
    }
    public Map<String, Object> getEligibleThemeService(String themeId) {
        ThemeModel themeDB = themeMapper.selectThemeById(themeId);
        if (themeDB == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(themeDB);
    }

    public Map<String, Object> uploadThemeService(String uuid, ThemeModel request){
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(2, uuid);
        if (userArchive.getSafe_level() <= 0){
            return new ErrorMap().archiveFreeze();
        }
        UserPermiModel userPermiRecord = getUserPermiFromDB.getUserPermiRecordService(uuid);
        if (userPermiRecord == null) {
            boolean b = manageUserMapper.insertUserPermi(uuid, PermiConst.USER + PermiConst.CREATOR);
            if (!b) {
                return new ErrorMap().errorMap("权限写入异常！");
            }
        } else {
            boolean b = userPermi.matchPermission(userPermiRecord.getPermission(), PermiConst.CREATOR);
            if (!b){
                boolean b1 = manageUserMapper.updateUserPermi(uuid, userPermiRecord.getPermission() + PermiConst.CREATOR);
                if (!b1) {
                    return new ErrorMap().errorMap("权限写入异常！");
                }
            }
        }
        request.setAuthor(uuid);
        boolean b = themeMapper.insertTheme(request);
        if (b){
            return new SuccessMap().uploadUpdateDeleteSuccessMap(0);
        }
        return new ErrorMap().uploadUpdateDeleteErrorMap(0);
    }
}
