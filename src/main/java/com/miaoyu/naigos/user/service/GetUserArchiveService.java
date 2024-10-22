package com.miaoyu.naigos.user.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 获取用户的头像
     * @param uuid 用户的唯一识别符
     * @return Map->JSON
     * */
    public Map<String, Object> getMeAvatarService(String uuid){
        UserArchiveModel userArchiveByUuid = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        if (userArchiveByUuid == null) {
            return new ErrorMap().errorMap("未找到用户！");
        }
        String avatarUrl = userArchiveByUuid.getAvatar();
        return new NormalMap().normalSuccessMap(avatarUrl);
    }

    /**
     * 获取用户积分和好感度
     * @param uuid 用户的唯一识别符
     * @return Map->JSON
     * */
    public Map<String, Object> getMeScoreService(String uuid) {
        UserArchiveModel userArchiveByUuid = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        if (userArchiveByUuid == null) {
            return new ErrorMap().errorMap("未找到用户！");
        }
        int score = userArchiveByUuid.getScore();
        int favorite = userArchiveByUuid.getFavorite();
        Map<String, Integer> dates = new HashMap<String, Integer>();
        dates.put("score", score);
        dates.put("favorite", favorite);
        return new NormalMap().normalSuccessMap(dates);

    }
}
