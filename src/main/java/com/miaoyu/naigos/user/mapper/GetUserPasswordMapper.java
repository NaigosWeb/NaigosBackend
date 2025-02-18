package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserPasswordModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface GetUserPasswordMapper {
    UserPasswordModel getUserPasswordTable(String uuid);
    boolean createUserPasswordRecode(String uuid);
    boolean createUserPasswordRecodeWithPassword(String uuid, String password);
    boolean createUserPasswordRecodeWithCode(String uuid, String code, long expirationDate);
    boolean updateUserPasswordCode(String uuid, String code, long expirationDate);
    boolean checkUserCodeSignin(String uuid);

    @Update("UPDATE users_password SET password = #{password} WHERE uuid = #{uuid}")
    boolean updateUserPassword(@Param("uuid") String uuid, @Param("password") String password);
    @Insert("INSERT INTO users_password (uuid, password, is_code) VALUES (#{uuid}, #{password}, 0)")
    boolean insertUserPassword(@Param("uuid") String uuid, @Param("password") String password);
}
