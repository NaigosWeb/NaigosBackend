package com.miaoyu.naigosbackend.api.service;

import com.miaoyu.naigosbackend.api.entity.SgClassifyBriefEntity;
import com.miaoyu.naigosbackend.api.mapper.GetSgClassifyMapper;
import com.miaoyu.naigosbackend.model.SogouInputThemeClassifyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSgClassifyService {
    @Autowired
    private GetSgClassifyMapper getSgClassifyMapper;

    public List<SgClassifyBriefEntity> findAllBriefSgClassify() {
        return getSgClassifyMapper.getAllBriefSgClassify();
    }
    public SogouInputThemeClassifyModel findSgClassifyById(String classifyId) {
        return getSgClassifyMapper.getSogouInputThemeClassify(classifyId);
    }
}
