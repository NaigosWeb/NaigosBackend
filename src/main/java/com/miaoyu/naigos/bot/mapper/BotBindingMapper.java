package com.miaoyu.naigos.bot.mapper;

import com.miaoyu.naigos.model.UserExchangeUuidModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface BotBindingMapper {
    @Select("SELECT * FROM users_exchange_uuid WHERE web_uuid = #{web_uuid}")
    UserExchangeUuidModel selectUserExchangeUuidByWebUuid(@Param("web_uuid") String web_uuid);

    @Select("SELECT * FROM users_exchange_uuid WHERE group_uuid = #{group_uuid}")
    UserExchangeUuidModel selectUserExchangeUuidByGroupUuid(@Param("group_uuid") String group_uuid);

    @Insert("INSERT INTO users_exchange_uuid (web_uuid, group_uuid, code, is_code, expiration) VALUES (#{web_uuid}, #{group_uuid}, #{code}, #{is_code}, #{expiration})")
    boolean insertUserExchangeCode(UserExchangeUuidModel uu);

    @Update("UPDATE users_exchange_uuid SET group_uuid = #{group_uuid} WHERE code = #{code}")
    boolean updateUserGroupUuidByCode(@Param("group_uuid") String group_uuid, @Param("code") String code);
    @Update("UPDATE users_exchange_uuid SET code = #{code} WHERE web_uuid = #{web_uuid}")
    boolean updateUserCodeByWebUuid(@Param("web_uuid") String web_uuid, @Param("code") String code);
}
