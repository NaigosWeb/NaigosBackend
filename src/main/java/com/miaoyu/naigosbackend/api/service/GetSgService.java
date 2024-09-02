package com.miaoyu.naigosbackend.api.service;

import com.miaoyu.naigosbackend.api.entity.SgBriefEntity;
import com.miaoyu.naigosbackend.api.mapper.GetSgMapper;
import com.miaoyu.naigosbackend.model.SogouInputThemeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetSgService {
    @Autowired
    private GetSgMapper getSgMapper;

    public List<SogouInputThemeModel> findAllSg(){
        return getSgMapper.getAll();
    }
    public List<SgBriefEntity> findAllBriefSg(){
        return getSgMapper.getAllBrief();
    }
    public SogouInputThemeModel findSgById(String themeId){
        return getSgMapper.getSgById(themeId);
    }
    public List<SgBriefEntity> findAllBriefSgByAppoint(String classify){
        return getSgMapper.getAllBriefByAppoint(classify);
    }
}
