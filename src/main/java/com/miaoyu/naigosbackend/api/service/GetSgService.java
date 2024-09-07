package com.miaoyu.naigosbackend.api.service;

import com.miaoyu.naigosbackend.api.entity.SgBriefEntity;
import com.miaoyu.naigosbackend.api.mapper.GetSgMapper;
import com.miaoyu.naigosbackend.constantsMap.ErrorMap;
import com.miaoyu.naigosbackend.constantsMap.NormalMap;
import com.miaoyu.naigosbackend.model.SogouInputThemeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetSgService {
    @Autowired
    private GetSgMapper getSgMapper;

    public Map<String, Object> findAllSg(){
        List<SogouInputThemeModel> all = getSgMapper.getAll();
        if (all != null && !all.isEmpty()){
            return new NormalMap().normalSuccessMap(all);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    public Map<String, Object> findAllBriefSg(){
        List<SgBriefEntity> allBrief = getSgMapper.getAllBrief();
        if (allBrief != null && !allBrief.isEmpty()){
            return new NormalMap().normalSuccessMap(allBrief);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    public Map<String, Object> findSgById(String themeId){
        SogouInputThemeModel sgById = getSgMapper.getSgById(themeId);
        if (sgById != null){
            return new NormalMap().normalSuccessMap(sgById);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    public Map<String, Object> findAllBriefSgByAppoint(String classify){
        List<SgBriefEntity> allBriefByAppoint = getSgMapper.getAllBriefByAppoint(classify);
        if (allBriefByAppoint != null && !allBriefByAppoint.isEmpty()){
            return new NormalMap().normalSuccessMap(allBriefByAppoint);
        }
        return new ErrorMap().errorMap("获取失败");
    }
}
