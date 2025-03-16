package com.miaoyu.naigos.bot.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import org.apache.ibatis.annotations.Insert;

public interface BotServiceMapper {
    @Insert("INSERT INTO users_users (qq_id, group_real_user_id, nickname, city, score, favorite, avatar, email, safe_level) VALUES (#{qq_id}, #{group_real_user_id}, #{nickname}, #{city}, #{score}, #{favorite}, #{avatar}, #{email}, #{safe_level})")
    boolean botInsertArchive(UserArchiveModel archive);
}
