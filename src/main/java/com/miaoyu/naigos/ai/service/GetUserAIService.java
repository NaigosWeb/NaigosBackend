package com.miaoyu.naigos.ai.service;

import com.miaoyu.naigos.ai.entity.UserAIEntity;
import com.miaoyu.naigos.ai.map.AIErrorMap;
import com.miaoyu.naigos.ai.map.AISuccessMap;
import com.miaoyu.naigos.ai.mapper.UserAIMapper;
import com.miaoyu.naigos.bot.mapper.BotBindingMapper;
import com.miaoyu.naigos.model.UserExchangeUuidModel;
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
        if (userAI.getExpiration_session() <= System.currentTimeMillis()) {
            userAI.setMax_session(10);
            userAI.setExpiration_session(null);
            boolean b = userAIMapper.restoreMaxSessionByGroupUuid(userAI);
            if (b) {
                return new AISuccessMap().ok(userAI);
            }
            return new AIErrorMap().recordError();
        }
        return new AISuccessMap().ok(userAI);
    }
}
