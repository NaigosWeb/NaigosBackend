package com.miaoyu.naigos.ai.controller;

import com.miaoyu.naigos.ai.service.GetUserAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
/**获取用户的AI设定*/
@RestController
@RequestMapping("/ai")
public class GetUserAIController {
    @Autowired
    private GetUserAIService getUserAIService;

    @GetMapping("/current_bot")
    public Map<String, Object> getUserAICurrentBotControl(
        @RequestParam(value = "uuid", required = false) String uuid
    ) {
        return getUserAIService.getUserAIBotService(uuid);
    }
}
