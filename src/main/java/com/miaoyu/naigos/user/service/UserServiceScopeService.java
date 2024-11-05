package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserServiceScopeModel;
import com.miaoyu.naigos.user.entity.UserServiceScopeEntity;
import com.miaoyu.naigos.user.mapper.UserServiceScopeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceScopeService {
    @Autowired
    private UserServiceScopeMapper userServiceScopeMapper;

    public Map<String, Object> getMeServiceScopeService(String uuid) {
        List<UserServiceScopeEntity> list = new ArrayList<>();
        UserServiceScopeModel userServiceScope = userServiceScopeMapper.getServiceScopeByUuid(uuid);
        if (userServiceScope == null) {
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("naigos");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
                return new SuccessMap().standardSuccessMap(list);
            }
        }
        if (userServiceScope.isNaigos()){
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("naigos");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
            }
        }
        if (userServiceScope.isTheme()){
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("theme");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
            }
        }
        if (userServiceScope.isBeautify()){
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("beautify");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
            }
        }
        if (userServiceScope.isBlue_archive()){
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("blue_archive");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
            }
        }
        if (userServiceScope.isNaigos_apply()){
            UserServiceScopeEntity ss = userServiceScopeMapper.getOnlyServiceScopeByServiceName("naigos_apply");
            if (ss == null) {
                return new ErrorMap().resourceNotExist();
            } else {
                list.add(ss);
            }
        }
        return new SuccessMap().standardSuccessMap(list);
    }
}
