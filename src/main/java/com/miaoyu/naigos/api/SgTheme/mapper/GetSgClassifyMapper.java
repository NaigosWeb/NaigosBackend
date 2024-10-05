package com.miaoyu.naigos.api.SgTheme.mapper;

import com.miaoyu.naigos.api.SgTheme.entity.SgClassifyBriefEntity;
import com.miaoyu.naigos.model.SogouInputThemeClassifyModel;

import java.util.List;

public interface GetSgClassifyMapper {
    List<SgClassifyBriefEntity> getAllBriefSgClassify();
    SogouInputThemeClassifyModel getSogouInputThemeClassify(String classifyId);
}
