package com.miaoyu.naigos.ai.controller;

import com.miaoyu.naigos.ai.entity.UserAIEntity;
import com.miaoyu.naigos.ai.service.RecordUserAIService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
/**记录用户的AI设定数值*/
@RestController
@RequestMapping("/ai/record")
public class RecordUserAIController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private RecordUserAIService recordUserAIService;

    @PostMapping("name_bot")
    public Map<String, Object> recordUserAINameControl(
            @RequestHeader("Authorization") String token,
            @RequestBody UserAIEntity requestBody
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "bot");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return recordUserAIService.recordUserAINameBotService(requestBody);
    }
}
