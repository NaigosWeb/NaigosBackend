package com.miaoyu.naigos.api.BlueArchive.service;

import com.miaoyu.naigos.api.Theme.entity.ThemeBriefEntity;
import com.miaoyu.naigos.api.Theme.mapper.SubcategoryMapper;
import com.miaoyu.naigos.api.Theme.mapper.ThemeMapper;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GetBAThemeListService {
    @Autowired
    private ThemeMapper themeMapper;
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @Autowired
    private SubcategoryMapper subcategoryMapper;

    public Map<String, Object> getAllThemeByRecreate(String classifyId) {
        List<ThemeBriefEntity> allEligibleThemeBrief = themeMapper.selectAllEligibleThemeBrief(classifyId);
        List<ThemeBriefEntity> utilEligibleThemeBrief = new ArrayList<>();
        String uuid = getUserArchiveService.getUserArchive(3).getGroup_real_user_id();
        String categoryValue = subcategoryMapper.selectSubcategory("blue_archive").getCategory_value();
        for (ThemeBriefEntity themeBriefEntity : allEligibleThemeBrief) {
            if (themeBriefEntity.getAuthor().equals(uuid) && themeBriefEntity.getTheme_id().split("_")[1].equals(categoryValue)) {
                utilEligibleThemeBrief.add(themeBriefEntity);
            }
        }
        return new SuccessMap().standardSuccessMap(utilEligibleThemeBrief);
    }
    public Map<String, Object> getAllThemeByRecord(String classifyId) {
        List<ThemeBriefEntity> allEligibleThemeBrief = themeMapper.selectAllEligibleThemeBrief(classifyId);
        List<ThemeBriefEntity> utilEligibleThemeBrief = new ArrayList<>();
        String categoryValue = subcategoryMapper.selectSubcategory("blue_archive").getCategory_value();
        for (ThemeBriefEntity themeBriefEntity : allEligibleThemeBrief) {
            if (themeBriefEntity.getTheme_id().split("_")[1].equals(categoryValue)) {
                utilEligibleThemeBrief.add(themeBriefEntity);
            }
        }
        return new SuccessMap().standardSuccessMap(utilEligibleThemeBrief);
    }

}
