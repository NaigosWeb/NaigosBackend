package com.miaoyu.naigosbackend.api.mapper;

import com.miaoyu.naigosbackend.api.entity.SgBriefEntity;
import com.miaoyu.naigosbackend.model.SogouInputThemeModel;

import java.util.List;

public interface GetSgMapper {
    List<SogouInputThemeModel> getAll();
    List<SgBriefEntity> getAllBrief();
    List<SgBriefEntity> getAllBriefByAppoint(String classifyId);
    SogouInputThemeModel getSgById(String themeId);
}
