package com.miaoyu.naigos.bot.service;

import com.miaoyu.naigos.bot.mapper.BotBindingMapper;
import com.miaoyu.naigos.bot.mapper.BotServiceMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserExchangeUuidModel;
import com.miaoyu.naigos.model.UserPasswordModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import com.miaoyu.naigos.user.mapper.GetUserPasswordMapper;
import com.miaoyu.naigos.user.service.UserCheckinService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotServiceService {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private BotServiceMapper botServiceMapper;
    @Autowired
    private UserCheckinService userCheckinService;
    @Autowired
    private GetUserPasswordMapper getUserPasswordMapper;
    @Autowired
    private BotBindingMapper botBindingMapper;

    public Map<String, Object> botServiceRegisterService(String uuid, long qqId) {
        UserArchiveModel userArchiveByUuid = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        UserArchiveModel userArchiveByQqId = getUserArchiveMapper.getUserArchiveByQqId(qqId);
        UserExchangeUuidModel userExchangeUuid = botBindingMapper.selectUserExchangeUuidByGroupUuid(uuid);
        if (userExchangeUuid != null) {
            UserArchiveModel userArchiveByExchange = getUserArchiveMapper.getUserArchiveByUuid(userExchangeUuid.getWeb_uuid());
            if (userArchiveByExchange != null) {
                return new ErrorMap().errorMap("您的UUID已与网站UUID互相绑定！");
            }
        }
        if (userArchiveByUuid != null) {
            return new ErrorMap().errorMap("您的UUID已存在！");
        }
        if (userArchiveByQqId != null) {
            return new ErrorMap().errorMap("您的QQ已存在！");
        }
        UserArchiveModel userArchive = getUserArchiveModel(uuid, qqId);
        boolean b = botServiceMapper.botInsertArchive(userArchive);
        if (!b) {
            return new ErrorMap().errorMap("注册失败！");
        }
        return new SuccessMap().standardSuccessMap("注册成功！");
    }

    @NotNull
    private static UserArchiveModel getUserArchiveModel(String uuid, long qqId) {
        UserArchiveModel userArchive = new UserArchiveModel();
        userArchive.setQq_id(qqId);
        userArchive.setGroup_real_user_id(uuid);
        userArchive.setEmail("未知@naigos.cn");
        userArchive.setCity("未知地点");
        userArchive.setNickname("未知名称");
        userArchive.setFavorite(50);
        userArchive.setScore(0);
        userArchive.setAvatar("https://q1.qlogo.cn/g?b=qq&nk=" + userArchive.getQq_id().toString() + "&s=640");
        userArchive.setSafe_level(10);
        userArchive.setIs_bot_memory(true);
        return userArchive;
    }

    public Map<String, Object> botServiceCheckInService(String uuid) {
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        UserExchangeUuidModel userExchangeUuid = botBindingMapper.selectUserExchangeUuidByGroupUuid(uuid);
        // 主档案表为空
        if (userArchive == null) {
            // 转换表为空
            if (userExchangeUuid == null) {
                // 确定为空
                return new ErrorMap().noSuchArchive();
            }
            // 转换表不为空
            else {
                userArchive = getUserArchiveMapper.getUserArchiveByUuid(userExchangeUuid.getWeb_uuid());
                // 转变表查询后主档案表为空
                if (userArchive == null) {
                    return new ErrorMap().noSuchArchive();
                } else {
                    uuid = userExchangeUuid.getWeb_uuid();
                }
            }
        }
        return userCheckinService.webCheckinService(uuid);
    }
    public Map<String, Object> botServiceCurrentService(String uuid) {
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        UserExchangeUuidModel userExchangeUuid = botBindingMapper.selectUserExchangeUuidByGroupUuid(uuid);
        // 主档案表为空
        if (userArchive == null) {
            // 转换表为空
            if (userExchangeUuid == null) {
                // 确定为空
                return new ErrorMap().noSuchArchive();
            }
            // 转换表不为空
            else {
                userArchive = getUserArchiveMapper.getUserArchiveByUuid(userExchangeUuid.getWeb_uuid());
                // 转变表查询后主档案表为空
                if (userArchive == null) {
                    return new ErrorMap().noSuchArchive();
                }
            }
        }
        return new SuccessMap().standardSuccessMap(userArchive);
    }
    public Map<String, Object> botServiceNopwdLoginService(String uuid, String code) {
        UserPasswordModel userPassword = getUserPasswordMapper.getUserPasswordTable(uuid);
        UserExchangeUuidModel userExchange = botBindingMapper.selectUserExchangeUuidByGroupUuid(uuid);
        if (userPassword == null) {
            userPassword = getUserPasswordMapper.getUserPasswordTable(userExchange.getWeb_uuid());
            if (userPassword == null) {
                return new ErrorMap().noSuchArchive();
            } else {
                uuid = userExchange.getWeb_uuid();
            }
        }
        String codeDB = userPassword.getCode();
        if (!code.equals(codeDB)) {
            return new ErrorMap().errorMap("验证码不正确！");
        }
        boolean b = botServiceMapper.botUpdateIsNopwdLogin(uuid, true);
        if (!b) {
            return new ErrorMap().errorMap("登录貌似失败！");
        }
        return new SuccessMap().standardSuccessMap("登录成功");
    }
}
