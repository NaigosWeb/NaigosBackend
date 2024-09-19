package com.miaoyu.naigosbackend.bot.controller;

import com.miaoyu.naigosbackend.bot.service.BotSigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/bot")
public class BotSigninController {
    @Autowired
    private BotSigninService botSigninService;

    @PostMapping("/signin")
    public Map<String, Object> signin(
            @RequestParam("bot_appid") int botAppid,
            @RequestParam("password") String password
    ) {
        return botSigninService.utilBotSignin(botAppid, password);
    }
}
