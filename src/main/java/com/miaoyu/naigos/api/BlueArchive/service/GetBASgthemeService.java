package com.miaoyu.naigos.api.BlueArchive.service;

import com.miaoyu.naigos.api.Theme.entity.ThemeBriefEntity;
import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetBASgthemeService {
    @Autowired private ThemeMapper themeMapper;
    @Autowired private GetUserArchiveService getUserArchiveService;
    public Map<String, Object> getAllBASgthemeBriefByRecordService() {
        List<ThemeBriefEntity> allEligibleThemeBriefDB = themeMapper.selectAllEligibleThemeBrief("sogou_input_theme");
        List<ThemeBriefEntity> utilEligibleThemeBrief = new ArrayList<>();
        for (ThemeBriefEntity themeBriefEntity : allEligibleThemeBriefDB) {
            if (themeBriefEntity.getTheme_id().split("_")[1].equals("BA")) {
                utilEligibleThemeBrief.add(themeBriefEntity);
            }
        }
        return new SuccessMap().standardSuccessMap(utilEligibleThemeBrief);
    }
    public Map<String, Object> getAllBASgthemeBriefByRecreateService() {
        List<ThemeBriefEntity> allEligibleThemeBriefDB = themeMapper.selectAllEligibleThemeBrief("sogou_input_theme");
        String uuid = getUserArchiveService.getUserArchive(3).getGroup_real_user_id();
        List<ThemeBriefEntity> utilEligibleThemeBrief = new ArrayList<>();
        for (ThemeBriefEntity themeBriefEntity : allEligibleThemeBriefDB) {
            if (themeBriefEntity.getAuthor().equals(uuid)
                    && themeBriefEntity.getTheme_id().split("_")[1].equals("BA")) {
                utilEligibleThemeBrief.add(themeBriefEntity);
            }
        }
        return new SuccessMap().standardSuccessMap(utilEligibleThemeBrief);
    }
}
