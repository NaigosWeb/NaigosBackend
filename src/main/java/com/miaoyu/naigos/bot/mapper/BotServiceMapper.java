package com.miaoyu.naigos.bot.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserPasswordModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface BotServiceMapper {
    @Insert("INSERT INTO users_users (qq_id, group_real_user_id, nickname, city, score, favorite, avatar, email, safe_level, is_bot_memory) VALUES (#{qq_id}, #{group_real_user_id}, #{nickname}, #{city}, #{score}, #{favorite}, #{avatar}, #{email}, #{safe_level}, #{is_bot_memory})")
    boolean botInsertArchive(UserArchiveModel archive);

    @Update("UPDATE users_password SET is_code = #{is_code} WHERE uuid = #{uuid}")
    boolean botUpdateIsNopwdLogin(@Param("uuid") String uuid, @Param("is_code") boolean isCode);
}
