package com.miaoyu.naigos.api.SgTheme.service;

import com.miaoyu.naigos.api.SgTheme.mapper.GetBaRecordClassifyMapper;
import com.miaoyu.naigos.api.SgTheme.model.BaRecordClassifyModel;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BARecordService {
    @Autowired
    private GetBaRecordClassifyMapper getBaRecordClassifyMapper;

    public Map<String, Object> getAllClassifyBriefService(){
        return new SuccessMap().standardSuccessMap(getBaRecordClassifyMapper.getAllBriefBaRecordClassify());
    }

    public Map<String, Object> getOnlyClassifyService(String classifyId){
       BaRecordClassifyModel recordClassify = getBaRecordClassifyMapper.getOnlyBaRecordClassify(classifyId);
       if (recordClassify == null){
           return new ErrorMap().errorMap("未找到该类！");
       }
       return new SuccessMap().standardSuccessMap(recordClassify);
    }
}
