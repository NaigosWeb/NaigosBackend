package com.miaoyu.naigos.bot.service;

import com.miaoyu.naigos.bot.mapper.BotServiceMapper;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotServiceService {
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private BotServiceMapper botServiceMapper;

    public Map<String, Object> botServiceRegisterService(String uuid, long qqId) {
        UserArchiveModel userArchiveByUuid = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        UserArchiveModel userArchiveByQqId = getUserArchiveMapper.getUserArchiveByQqId(qqId);
        if (userArchiveByUuid != null) {
            return new ErrorMap().errorMap("您的UUID已存在！");
        }
        if (userArchiveByQqId != null) {
            return new ErrorMap().errorMap("您的QQ已存在！");
        }
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
        boolean b = botServiceMapper.botInsertArchive(userArchive);
        if (!b) {
            return new ErrorMap().errorMap("注册失败！");
        }
        return new SuccessMap().standardSuccessMap("注册成功！");
    }
}
