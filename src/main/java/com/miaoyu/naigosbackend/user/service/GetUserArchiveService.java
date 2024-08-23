package com.miaoyu.naigosbackend.user.service;

import com.miaoyu.naigosbackend.AppConfig;
import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserArchiveService {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

    // 使用Integer作为uid
    public UserArchiveModel getUserArchive(Integer uniqueId) {
        return getUserArchiveMapper.getUserArchiveByUniqueId(uniqueId);
    }

    // 使用String作为uid，这里假设email和uuid都通过String传递
    public UserArchiveModel getUserArchive(int loginType, String uid) {
        return switch (loginType) {
            case 1 -> getUserArchiveMapper.getUserArchiveByEmail(uid);
            case 2 -> getUserArchiveMapper.getUserArchiveByUuid(uid);
            default -> null;
        };
    }

}
