package com.miaoyu.naigos.api.SgTheme.mapper;

import com.miaoyu.naigos.api.SgTheme.entity.SgBriefEntity;
import com.miaoyu.naigos.model.SogouInputThemeModel;

import java.util.List;

public interface GetSgMapper {
    List<SogouInputThemeModel> getAll();
    List<SgBriefEntity> getAllBrief();
    List<SgBriefEntity> getAllBriefByAppoint(String classifyId);
    SogouInputThemeModel getSgById(String themeId);
}
