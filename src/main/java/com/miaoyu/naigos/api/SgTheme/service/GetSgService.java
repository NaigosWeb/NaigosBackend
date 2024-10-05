package com.miaoyu.naigos.api.SgTheme.service;

import com.miaoyu.naigos.api.SgTheme.entity.SgBriefEntity;
import com.miaoyu.naigos.api.SgTheme.mapper.GetSgMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.SogouInputThemeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetSgService {
    @Autowired
    private GetSgMapper getSgMapper;

    /// 服务层 获取所有搜狗输入法皮肤的详情信息
    public Map<String, Object> findAllSg(){
        List<SogouInputThemeModel> all = getSgMapper.getAll();
        if (all != null && !all.isEmpty()){
            return new NormalMap().normalSuccessMap(all);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    /// 服务层 获取所有搜狗输入法皮肤的简略信息
    public Map<String, Object> findAllBriefSg(){
        List<SgBriefEntity> allBrief = getSgMapper.getAllBrief();
        if (allBrief != null && !allBrief.isEmpty()){
            return new NormalMap().normalSuccessMap(allBrief);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    /// 服务层 根据搜狗输入法皮肤ID专一获取该皮肤详情信息
    public Map<String, Object> findSgById(String themeId){
        SogouInputThemeModel sgById = getSgMapper.getSgById(themeId);
        if (sgById != null){
            return new NormalMap().normalSuccessMap(sgById);
        }
        return new ErrorMap().errorMap("获取失败");
    }
    /// 服务层 根据分类ID获取符合条件的搜狗输入法皮肤的简略信息
    public Map<String, Object> findAllBriefSgByAppoint(String classify){
        List<SgBriefEntity> allBriefByAppoint = getSgMapper.getAllBriefByAppoint(classify);
        if (allBriefByAppoint != null && !allBriefByAppoint.isEmpty()){
            return new NormalMap().normalSuccessMap(allBriefByAppoint);
        }
        return new ErrorMap().errorMap("获取失败");
    }
}
