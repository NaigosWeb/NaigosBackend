package com.miaoyu.naigos.ai.service;

import com.miaoyu.naigos.ai.entity.UserAIEntity;
import com.miaoyu.naigos.ai.map.AIErrorMap;
import com.miaoyu.naigos.ai.map.AISuccessMap;
import com.miaoyu.naigos.ai.mapper.UserAIMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GetUserAIService {
    @Autowired
    private UserAIMapper userAIMapper;

    public Map<String, Object> getUserAIBotService(String uuid) {
        if (uuid == null) {
            return new AISuccessMap().noSuchUserSuccess();
        }
        UserAIEntity userAI = userAIMapper.selectByGroupUuid(uuid);
        if (userAI == null) {
            return new AISuccessMap().noSuchUserSuccess();
        }
        if (10 < userAI.getMax_session()) {
            if (userAI.getExpiration_session() < System.currentTimeMillis()) {
                boolean b = userAIMapper.restoreMaxSessionByGroupUuid(uuid);
                if (b) {
                    return new AISuccessMap().ok(userAI);
                }
                return new AIErrorMap().recordError();
            }
        }
        return new AISuccessMap().ok(userAI);
    }
}
