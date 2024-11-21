package com.miaoyu.naigos.api.Theme.service;

import com.miaoyu.naigos.api.Theme.mapper.ThemeClassifyMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.ThemeClassifyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ThemeClassifyService {
    @Autowired
    private ThemeClassifyMapper themeClassifyMapper;

    public Map<String, Object> getAllThemeClassifyBriefService() {
        return new SuccessMap().standardSuccessMap(themeClassifyMapper.getAllThemeClassifyBrief());
    }

    public Map<String, Object> getEligibleClassifyService(String classifyId){
        ThemeClassifyModel themeEligibleClassify = themeClassifyMapper.getThemeEligibleClassify(classifyId);
        if (themeEligibleClassify == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(themeEligibleClassify);
    }
}
