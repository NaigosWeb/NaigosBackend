package com.miaoyu.naigosbackend.user.service;

import com.miaoyu.naigosbackend.constantsMap.ErrorMap;
import com.miaoyu.naigosbackend.constantsMap.NormalMap;
import com.miaoyu.naigosbackend.jwtHandle.JwtSigned;
import com.miaoyu.naigosbackend.model.UserArchiveModel;
import com.miaoyu.naigosbackend.model.UserPasswordModel;
import com.miaoyu.naigosbackend.service.AppService;
import com.miaoyu.naigosbackend.user.mapper.GetUserPasswordMapper;
import com.miaoyu.naigosbackend.user.mapper.IsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

@Service
public class UserSignInAndUpService {
    @Autowired
    private IsUserMapper isUserMapper;
    @Autowired
    private GetUserArchiveService getUserArchiveService;
    @Autowired
    private AppService appService;
    @Autowired
    private GetUserPasswordMapper getUserPasswordMapper;
    @Autowired
    private JwtSigned jwtSigned;


    public Map<String, Object> isUserAndPwdInDatabase(String accountType, String account, String webPwd){
        UserArchiveModel userArchive = null;
        switch (accountType){
            case "id": {
                userArchive = getUserArchiveService.getUserArchive(Integer.parseInt(account));
                break;
            } case "email": {
                userArchive = getUserArchiveService.getUserArchive(1, account);
                break;
            } default: return null;
        }
        if (userArchive != null) {
            if (userArchive.getSafe_level() < 0){
                return null;
            }
            String uuid = userArchive.getGroup_real_user_id();
            Boolean isUserPasswordConsistent = isUserPasswordConsistent(uuid, webPwd);
            if (isUserPasswordConsistent) {
                return new NormalMap().normalSuccessMap(jwtSigned.jwtSigned("web", uuid));
            }
            return null;
        } else {
            return null;
        }
    }

    public String findArchiveAndCode(String accountType, String account){
        UserArchiveModel userArchive = null;
        switch (accountType){
            case "id": {
                userArchive = getUserArchiveService.getUserArchive(Integer.parseInt(account));
                break;
            } case "email": {
                userArchive = getUserArchiveService.getUserArchive(1, account);
                break;
            } default: return null;
        }
        if (userArchive != null){
            String uuid = userArchive.getGroup_real_user_id();
            UserPasswordModel userPasswordTable = getUserPasswordMapper.getUserPasswordTable(uuid);
            String gc = generateCode();
            if (userPasswordTable != null){
                boolean b = getUserPasswordMapper.updateUserPasswordCode(
                        uuid, gc, System.currentTimeMillis() + (3600 * 1000 * 24));
                if (b){
                    return gc;
                } else {
                    return null;
                }
            }
            boolean userPasswordRecodeWithCode = getUserPasswordMapper.createUserPasswordRecodeWithCode(
                    uuid, gc, System.currentTimeMillis() + (3600 * 1000 * 24));
            if (!userPasswordRecodeWithCode) {
                return gc;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Map<String, Object> nopwdSignin(UserArchiveModel userArchive, String code){
        if (userArchive != null) {
            String uuid = userArchive.getGroup_real_user_id();
            System.out.println("uuid" + uuid);
            UserPasswordModel userPasswordTable = getUserPasswordMapper.getUserPasswordTable(uuid);
            if (userPasswordTable != null) {
                String dbCode = userPasswordTable.getCode();
                if (dbCode == null) return new ErrorMap().errorMap("没有验证码");
                boolean dbIsCode = userPasswordTable.isIs_code();
                long dbExpirationDate = userPasswordTable.getExpiration_date();
                if (dbCode.equals(code) && dbIsCode && dbExpirationDate > System.currentTimeMillis()) {
                    boolean b = getUserPasswordMapper.checkUserCodeSignin(uuid);
                    if (b) {
                        return new NormalMap().normalSuccessMap(jwtSigned.jwtSigned("web", uuid));
                    }
                    return new ErrorMap().errorMap("签发失败");
                }
                return new ErrorMap().errorMap("数据错误");
            }
            return new ErrorMap().errorMap("没有验证码数据");
        }
        return new ErrorMap().errorMap("ID不存在");
    }

    private Boolean isUserPasswordConsistent(String uuid, String webPwd){
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
    private String generateCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(10);
            sb.append(randomNum);
        }
        return sb.toString();
    }
}
