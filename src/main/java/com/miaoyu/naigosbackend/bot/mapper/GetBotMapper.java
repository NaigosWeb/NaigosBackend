package com.miaoyu.naigosbackend.bot.mapper;

import com.miaoyu.naigosbackend.model.BotArchiveModel;

public interface GetBotMapper {
    BotArchiveModel getBot(int botAppid);
}
