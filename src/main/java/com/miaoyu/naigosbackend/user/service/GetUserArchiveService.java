package com.miaoyu.naigosbackend.user.service;

import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserArchiveService {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

    /**
     * 根据UID获取用户信息
     * param
     *  uniqueId: Integer 唯一UID*/
    public UserArchiveModel getUserArchive(Integer uniqueId) {
        return getUserArchiveMapper.getUserArchiveByUniqueId(uniqueId);
    }
    /**
     * 根据账号规则获取用户信息
     * param
     *  loginType: int 账号规则 1:email 2:uuid
     *  uid: String 账号*/
    public UserArchiveModel getUserArchive(int loginType, String uid) {
        return switch (loginType) {
            case 1 -> getUserArchiveMapper.getUserArchiveByEmail(uid);
            case 2 -> getUserArchiveMapper.getUserArchiveByUuid(uid);
            default -> null;
        };
    }

}
