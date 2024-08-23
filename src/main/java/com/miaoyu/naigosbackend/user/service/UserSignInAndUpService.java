package com.miaoyu.naigosbackend.user.service;

import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.model.UserPasswordModel;
import com.miaoyu.naigosbackend.user.mapper.IsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSignInAndUpService {
    @Autowired
    private IsUserMapper isUserMapper;
    @Autowired
    private GetUserArchiveService getUserArchiveService;


    public String isUserInDatabase(Integer uniqueId){
        String uuid;
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(uniqueId);
        if (userArchive != null) {
            uuid = userArchive.getGroup_real_user_id();
            return isUserPassword(uuid);
        } else {
            return null;
        }
    }
    public String isUserInDatabase(int loginType, String uid){
        String uuid;
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(loginType, uid);
        if (userArchive != null) {
            uuid = userArchive.getGroup_real_user_id();
            return isUserPassword(uuid);
        } else {
            return null;
        }
    }

    private String isUserPassword(String uuid){
        UserPasswordModel userPasswordModel = isUserMapper.isUserPasswordInDatabase(uuid);
        if (userPasswordModel != null) {
            return userPasswordModel.getPassword();
        } else {
            return null;
        }
    }
}
