package com.miaoyu.naigos.ai.service;

import com.miaoyu.naigos.ai.entity.UserAIEntity;
import com.miaoyu.naigos.ai.map.AIErrorMap;
import com.miaoyu.naigos.ai.map.AISuccessMap;
import com.miaoyu.naigos.ai.mapper.UserAIMapper;
import com.miaoyu.naigos.bot.mapper.BotBindingMapper;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserExchangeUuidModel;
import com.miaoyu.naigos.user.mapper.GetUserArchiveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecordUserAIService {
    @Autowired
    private UserAIMapper userAIMapper;
    @Autowired
    private GetUserArchiveMapper getUserArchiveMapper;
    @Autowired
    private BotBindingMapper botBindingMapper;

    public Map<String, Object> recordUserAINameBotService(UserAIEntity requestBody) {
        String uuid = requestBody.getUuid();
        UserArchiveModel userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
        // 不存在腾讯UUID档案
        if (userArchive == null) {
            UserExchangeUuidModel userExchange = botBindingMapper.selectUserExchangeUuidByGroupUuid(uuid);
            // 不存在交换UUID
            if (userExchange == null) {
                return new AIErrorMap().noSuchUserAI();
            }
            uuid = userExchange.getWeb_uuid();
            userArchive = getUserArchiveMapper.getUserArchiveByUuid(uuid);
            // 不存在奶果UUID档案
            if (userArchive == null) {
                return new AIErrorMap().noSuchUserAI();
            }
        }
        // 扣除小布丁
        final int RECORD_NAME_COST = 10000;
        if (userArchive.getScore() < RECORD_NAME_COST) {
            return new AIErrorMap().insufficientBalance();
        }
        userArchive.setScore(userArchive.getScore() - RECORD_NAME_COST);
        boolean b1 = getUserArchiveMapper.updateUserArchiveByUuid(userArchive.getQq_id(), userArchive.getNickname(), userArchive.getCity(), userArchive.getScore(), userArchive.getFavorite(), userArchive.getGroup_real_user_id());
        if (!b1) {
            return new AIErrorMap().no("扣除布丁失败！Sub pudding failed!");
        }
        // 记录进AI库
        requestBody.setName("###交流对象：我的名字叫" + requestBody.getName() + "，请结合设定与我交流。");
        UserAIEntity userAI = userAIMapper.selectByGroupUuid(requestBody.getUuid());
        if (userAI == null) {
            boolean b = userAIMapper.insertNameByGroupUuid(requestBody);
            if (!b) {
                return new AIErrorMap().recordError();
            }
            return new AISuccessMap().standardSuccess();
        }
        boolean b = userAIMapper.updateNameByGroupUuid(requestBody);
        if (!b) {
            return new AIErrorMap().recordError();
        }
        return new AISuccessMap().standardSuccess();
    }
}
