package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OtherUserService {
    @Autowired
    private GetUserArchiveService getUserArchiveService;

    public Map<String, Object> getOtherNicknameService(String uuid){
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(2, uuid);
        if (userArchive == null) {
            return new ErrorMap().noSuchArchive();
        }
        return new SuccessMap().standardSuccessMap(userArchive.getNickname());
    }

    public Map<String, Object> getOtherArchiveService(String uuid){
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(2, uuid);
        if (userArchive == null){
            return new ErrorMap().noSuchArchive();
        }
        return new SuccessMap().standardSuccessMap(userArchive);
    }
}
