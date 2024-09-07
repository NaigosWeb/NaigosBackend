package com.miaoyu.naigosbackend.api.service;

import com.miaoyu.naigosbackend.api.entity.SgClassifyBriefEntity;
import com.miaoyu.naigosbackend.api.mapper.GetSgClassifyMapper;
import com.miaoyu.naigosbackend.constantsMap.ErrorMap;
import com.miaoyu.naigosbackend.constantsMap.NormalMap;
import com.miaoyu.naigosbackend.model.SogouInputThemeClassifyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetSgClassifyService {
    @Autowired
    private GetSgClassifyMapper getSgClassifyMapper;

    public Map<String, Object> findAllBriefSgClassify() {
        List<SgClassifyBriefEntity> allBriefSgClassify = getSgClassifyMapper.getAllBriefSgClassify();
        if (allBriefSgClassify != null && !allBriefSgClassify.isEmpty()) {
            return new NormalMap().normalSuccessMap(allBriefSgClassify);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    public Map<String, Object> findSgClassifyById(String classifyId) {
        SogouInputThemeClassifyModel sogouInputThemeClassify = getSgClassifyMapper.getSogouInputThemeClassify(classifyId);
        if (sogouInputThemeClassify != null) {
            return new NormalMap().normalSuccessMap(sogouInputThemeClassify);
        }
        return new ErrorMap().errorMap("获取失败");
    }
}
