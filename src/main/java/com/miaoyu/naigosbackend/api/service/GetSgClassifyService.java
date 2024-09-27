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

    /// 服务层 获取所有搜狗输入法皮肤分类的简略信息
    public Map<String, Object> findAllBriefSgClassify() {
        List<SgClassifyBriefEntity> allBriefSgClassify = getSgClassifyMapper.getAllBriefSgClassify();
        if (allBriefSgClassify != null && !allBriefSgClassify.isEmpty()) {
            return new NormalMap().normalSuccessMap(allBriefSgClassify);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    /// 服务层 根据分类ID专一获取该分类的详情信息
    public Map<String, Object> findSgClassifyById(String classifyId) {
        SogouInputThemeClassifyModel sogouInputThemeClassify = getSgClassifyMapper.getSogouInputThemeClassify(classifyId);
        if (sogouInputThemeClassify != null) {
            return new NormalMap().normalSuccessMap(sogouInputThemeClassify);
        }
        return new ErrorMap().errorMap("获取失败");
    }
}
