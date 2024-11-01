package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.jwtHandle.JwtSigned;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserPasswordModel;
import com.miaoyu.naigos.service.AppService;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.user.mapper.GetUserPasswordMapper;
import com.miaoyu.naigos.user.mapper.IsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;

    /**
    * 处理账号是否存在数据库且密码是否正确
    * param
    *   accountType: String 账号的规则
    *   account: String 账号
    *   webPwd: String 网页中用户传来的密码*/
    public Map<String, Object> isUserAndPwdInDatabase(String accountType, String account, String webPwd){
        UserArchiveModel userArchive = getUserArchive(accountType, account);
        if (userArchive != null) {
            if (userArchive.getSafe_level() < 0){
                return new ErrorMap().errorMap("账号已被冻结！");
            }
            // 有账号并且没有被封禁
            String uuid = userArchive.getGroup_real_user_id();
            // 判断账号的密码是否正确
            Boolean isUserPasswordConsistent = isUserPasswordConsistent(uuid, webPwd);
            System.out.println(isUserPasswordConsistent);
            if (isUserPasswordConsistent) {
                return new NormalMap().normalSuccessMap(jwtSigned.jwtSigned("web", uuid));
            } else {
                return new ErrorMap().errorMap("密码错误！");
            }
        }
        return new ErrorMap().errorMap("未找到档案！");
    }

    /**
    * 处理账号是否存在并签发验证码
    * param
    *   accountType: String 账号规则
    *   account: String 账号*/
    public Map<String, Object> findArchiveAndCode(String accountType, String account){
        UserArchiveModel userArchive = getUserArchive(accountType, account);
        // 当账号存在
        if (userArchive != null){
            String uuid = userArchive.getGroup_real_user_id();
            UserPasswordModel userPasswordTable = getUserPasswordMapper.getUserPasswordTable(uuid);
            String gc = generateCode(); // 生成安全验证码
            if (userPasswordTable != null){
                // 若数据库中存在密码表
                boolean b = getUserPasswordMapper.updateUserPasswordCode(
                        uuid, gc, System.currentTimeMillis() + (3600 * 1000 * 24));
                if (b){
                    return new NormalMap().normalSuccessMap(gc);
                } else {
                    return new ErrorMap().errorMap("生成、写入验证码失败！");
                }
            }
            // 若数据库中不存在密码表则创建密码表并直接将验证码写入
            boolean userPasswordRecodeWithCode = getUserPasswordMapper.createUserPasswordRecodeWithCode(
                    uuid, gc, System.currentTimeMillis() + (3600 * 1000 * 24));
            if (!userPasswordRecodeWithCode) {
                return new NormalMap().normalSuccessMap(gc);
            } else {
                return new ErrorMap().errorMap("生成、写入验证码失败！");
            }
        } else {
            return new ErrorMap().errorMap("账号档案不存在！");
        }
    }

    /**
     * 服务层 确认无密码登录
     * param
     *  userArchive: UserArchiveModel 用户的账号详情信息
     *  code: String 验证码
     * */
    public Map<String, Object> nopwdSignin(String accountType, String account, String code){
        UserArchiveModel userArchive = getUserArchive(accountType, account);
        if (userArchive != null) {
            String uuid = userArchive.getGroup_real_user_id();
            System.out.println("uuid" + uuid);
            UserPasswordModel userPasswordTable = getUserPasswordMapper.getUserPasswordTable(uuid);
            if (userPasswordTable != null) {
                // 存在密码表
                String dbCode = userPasswordTable.getCode();
                if (dbCode == null) return new ErrorMap().errorMap("没有验证码");
                // 存在验证码
                boolean dbIsCode = userPasswordTable.isIs_code();
                long dbExpirationDate = userPasswordTable.getExpiration_date();
                if (dbCode.equals(code) && dbIsCode && dbExpirationDate > System.currentTimeMillis()) {
                    // 验证码相等 验证码已经被验证 验证码记录时间戳未过期
                    boolean b = getUserPasswordMapper.checkUserCodeSignin(uuid); //删除验证码 重置被验证 删除时间戳
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
    public Map<String, Object> userSignupService(String email,
                                                 String qqId,
                                                 String password){
        String nickname = "新用户" + email;
        UserArchiveModel userArchive = getUserArchiveService.getUserArchive(1, email);
        if (userArchive != null){
            return new ErrorMap().errorMap("该电子邮箱已被注册！");
        }
        userArchive = getUserArchiveService.getUserArchive(3, qqId);
        if (userArchive != null){
            return new ErrorMap().errorMap("该QQ号已被注册！");
        }
        String uuid;
        do {
            uuid = generateUuid(email);
            userArchive = getUserArchiveService.getUserArchive(2, uuid);
            if (userArchive != null){
                uuid = null;
            }
        } while (uuid == null);
        boolean b = getUserArchiveMapper.webRegisterUserArchiveByEmail(email, nickname, uuid, Long.valueOf(qqId));
        if (b){
            boolean userPasswordRecodeWithPassword = getUserPasswordMapper.createUserPasswordRecodeWithPassword(uuid, passwordHash(password));
            if (!userPasswordRecodeWithPassword) {
                return new NormalMap().normalSuccessMap("注册成功！但密码写入失败，这是严重问题，请寻找站长！");
            } else {
                String token = jwtSigned.jwtSigned("web", uuid);
                Map<String, Object> result = new HashMap<>();
                result.put("token", token);
                result.put("message", "注册成功！");
                return new NormalMap().normalSuccessMap(result);
            }
        } else {
            return new ErrorMap().errorMap("注册失败！");
        }
    }

    // 私有 根据邮箱地址和时间戳计算UUID
    private String generateUuid(String email){
        try {
            long timestamp = System.currentTimeMillis();
            String emailTimestampTogether = email + timestamp;
            UUID uuid = UUID.nameUUIDFromBytes(emailTimestampTogether.getBytes());
            return uuid.toString();
        } catch (Exception e) {
            return null;
        }
    }
    // 私有 获取用户档案
    private UserArchiveModel getUserArchive(String accountType, String account){
        return switch (accountType) {
            case "uid" -> getUserArchiveService.getUserArchive(Integer.parseInt(account));
            case "email" -> getUserArchiveService.getUserArchive(1, account);
            default -> null;
        };
    }
    // 私有 判断密码哈希值是否相等
    private Boolean isUserPasswordConsistent(String uuid, String webPwd){
        String dbPwd;
        UserPasswordModel userPasswordInDatabase = isUserMapper.isUserPasswordInDatabase(uuid);
        dbPwd = userPasswordInDatabase.getPassword();
        String hashPwd = passwordHash(webPwd);
        System.out.println("ServerSavePwd:" + dbPwd + "\nHashPwd:" + hashPwd);
        return dbPwd != null && dbPwd.equals(hashPwd);
    }
    // 私有 根据加盐值和SHA256计算密码的哈希值
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
    // 私有 生成安全验证码
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
