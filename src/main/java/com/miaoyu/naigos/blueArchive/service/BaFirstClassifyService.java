package com.miaoyu.naigos.blueArchive.service;

import com.miaoyu.naigos.blueArchive.mapper.BaFirstClassifyMapper;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BaFirstClassifyService {
    @Autowired
    private BaFirstClassifyMapper baFirstClassifyMapper;

    public Map<String, Object> getBaAllFirstClassifyService() {
        return new SuccessMap().standardSuccessMap(baFirstClassifyMapper.selectAll());
    }
}
