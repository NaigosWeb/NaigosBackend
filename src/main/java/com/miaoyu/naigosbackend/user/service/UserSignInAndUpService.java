package com.miaoyu.naigosbackend.user.service;

import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.model.UserPasswordModel;
import com.miaoyu.naigosbackend.service.AppService;
import com.miaoyu.naigosbackend.service.JwtService;
import com.miaoyu.naigosbackend.user.mapper.IsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserSignInAndUpService {
    @Autowired
    private IsUserMapper isUserMapper;
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @Autowired
    private AppService appService;


    public boolean isUserInDatabase(Integer uniqueId, String webPwd){
        String uuid;
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(uniqueId);
        if (userArchive != null) {
            uuid = userArchive.getGroup_real_user_id();
            return isUserPassword(uuid, webPwd);
        } else {
            return false;
        }
    }
    public boolean isUserInDatabase(int loginType, String uid, String webPwd){
        String uuid;
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(loginType, uid);
        if (userArchive != null) {
            uuid = userArchive.getGroup_real_user_id();
            return isUserPassword(uuid, webPwd);
        } else {
            return false;
        }
    }

    private Boolean isUserPassword(String uuid, String webPwd){
        String dbPwd;
        UserPasswordModel userPasswordInDatabase = isUserMapper.isUserPasswordInDatabase(uuid);
        dbPwd = userPasswordInDatabase.getPassword();
        String hashPwd = passwordHash(webPwd);

        System.out.println("ServerSavePwd:" + dbPwd + "\nHashPwd:" + hashPwd);
        return dbPwd != null && dbPwd.equals(hashPwd);
    }

    private String passwordHash(String target){
        String pwdKey = appService.getPwdKey();
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = target + pwdKey;
            byte[] encodedHash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b: encodedHash){
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e){
            return null;
        }
    }
}
