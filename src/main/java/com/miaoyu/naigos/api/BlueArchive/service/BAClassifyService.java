package com.miaoyu.naigos.api.BlueArchive.service;

import com.miaoyu.naigos.api.BlueArchive.mapper.RecreateBAClassifyMapper;
import com.miaoyu.naigos.api.BlueArchive.model.BARecreateClassifyModel;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BAClassifyService {
    @Autowired private RecreateBAClassifyMapper recreateBaClassifyMapper;
    public Map<String, Object> getAllBAClassifyBriefService() {
        return new SuccessMap().standardSuccessMap(recreateBaClassifyMapper.selectAllBAClassifyBrief());
    }

    public Map<String, Object> getEligibleBAClassify(String classifyId) {
        BARecreateClassifyModel classifyDB = recreateBaClassifyMapper.selectEligibleBAClassify(classifyId);
        if (classifyDB == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(classifyDB);
    }
}
