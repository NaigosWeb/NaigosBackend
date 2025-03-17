package com.miaoyu.naigos.bot.service;

import com.miaoyu.naigos.bot.mapper.BotBindingMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserExchangeUuidModel;
import com.miaoyu.naigos.service.AppService;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;

@Service
public class BotBindingService {
    @Autowired
    private AppService appService;
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private BotBindingMapper botBindingMapper;

    public Map<String, Object> botGetBindingCode (String uuid) {
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        // 没有档案
        if (userArchive == null) {
            return new ErrorMap().noSuchArchive();
        }
        // 生成哈希256验证码 与 设置记录
        String code = this.generateCode();
        // 判断是否被转换
        UserExchangeUuidModel exchangeByWeb = botBindingMapper.selectUserExchangeUuidByWebUuid(uuid);
        // 存在转换记录
        if (exchangeByWeb != null) {
            // 已转换
            if (!exchangeByWeb.getGroup_uuid().equals("no_binding")) {
                return new ErrorMap().errorMap("该网站UUID已经绑定QQBotUUID");
            }
            // 未转换
            boolean b = botBindingMapper.updateUserCodeByWebUuid(uuid, code);
            if (!b) {
                return new ErrorMap().errorMap("记录验证码失败！");
            }
            return new SuccessMap().standardSuccessMap(code);
        }
        // 新建临时模型对象
        UserExchangeUuidModel tempExchange = new UserExchangeUuidModel();
        tempExchange.setWeb_uuid(uuid);
        tempExchange.setGroup_uuid("no_binding");
        tempExchange.setCode(code);
        tempExchange.setExpiration(System.currentTimeMillis() + (3600 * 1000 * 24));
        // 不存在转换记录
        boolean b = botBindingMapper.insertUserExchangeCode(tempExchange);
        if (!b) {
            return new ErrorMap().errorMap("记录验证码失败！");
        }
        return new SuccessMap().standardSuccessMap(code);
    }

    // 私有 生成安全验证码
    private String generateCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(10);
            sb.append(randomNum);
        }
        return passwordHash(sb.toString());
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
}
