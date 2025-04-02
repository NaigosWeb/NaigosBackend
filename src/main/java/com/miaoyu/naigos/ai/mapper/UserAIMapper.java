package com.miaoyu.naigos.ai.mapper;

import com.miaoyu.naigos.ai.entity.UserAIEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAIMapper {
    @Select("SELECT * FROM users_ai WHERE uuid = #{uuid}")
    UserAIEntity selectByGroupUuid(@Param("uuid") String uuid);
    @Insert("INSERT INTO users_ai (uuid, name) VALUES (#{uuid}, #{name})")
    boolean insertNameByGroupUuid(UserAIEntity userAIEntity);
    @Update("UPDATE users_ai SET name = #{name} WHERE uuid = #{uuid}")
    boolean updateNameByGroupUuid(UserAIEntity userAIEntity);
    @Update("UPDATE users_ai SET max_session = 10, expiration_session = null WHERE uuid = #{uuid}")
    boolean restoreMaxSessionByGroupUuid(String uuid);
}
