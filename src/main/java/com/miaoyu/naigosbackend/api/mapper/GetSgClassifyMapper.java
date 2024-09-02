package com.miaoyu.naigosbackend.api.mapper;

import com.miaoyu.naigosbackend.api.entity.SgClassifyBriefEntity;
import com.miaoyu.naigosbackend.model.SogouInputThemeClassifyModel;

import java.util.List;

public interface GetSgClassifyMapper {
    List<SgClassifyBriefEntity> getAllBriefSgClassify();
    SogouInputThemeClassifyModel getSogouInputThemeClassify(String classifyId);
}
