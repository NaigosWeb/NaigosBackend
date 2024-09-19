package com.miaoyu.naigosbackend.bot.service;

import com.miaoyu.naigosbackend.bot.mapper.GetBotMapper;
import com.miaoyu.naigosbackend.constantsMap.ErrorMap;
import com.miaoyu.naigosbackend.constantsMap.NormalMap;
import com.miaoyu.naigosbackend.jwtHandle.JwtSigned;
import com.miaoyu.naigosbackend.model.BotArchiveModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotSigninService {
    @Autowired
    private GetBotMapper getBotMapper;
    @Autowired
    private JwtSigned jwtSigned;

    public Map<String, Object> utilBotSignin(int botAppid, String password){
        BotArchiveModel bot = getBotMapper.getBot(botAppid);
        if (bot == null){
            return new ErrorMap().errorMap("Bot信息未找到！");
        }
        if (!bot.getPassword().equals(password)){
            return new ErrorMap().errorMap("Bot密码不正确！");
        }
        if (bot.getSafe_level() <= 0) {
            return new ErrorMap().errorMap("Bot不安全或已被冻结！");
        }
        String botJws = jwtSigned.jwtSigned("bot", String.valueOf(botAppid));
        return new NormalMap().normalSuccessMap(botJws);
    }
}
