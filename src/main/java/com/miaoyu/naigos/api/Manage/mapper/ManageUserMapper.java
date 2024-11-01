package com.miaoyu.naigos.api.Manage.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.model.UserPermiModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ManageUserMapper {
    @Select("SELECT * FROM users_users")
    List<UserArchiveModel> getAllUser();

    @Select("SELECT * FROM user_permission WHERE uuid = #{uuid}")
    UserPermiModel getUserPermiByUuid(@Param("uuid") String uuid);

    @Update("UPDATE user_permission SET permission = #{permission} WHERE uuid = #{uuid}")
    boolean updateUserPermi(@Param("uuid") String uuid, @Param("permission") int permission);

    @Insert("INSERT INTO user_permission (uuid, permission) VALUES (#{uuid}, #{permission})")
    boolean insertUserPermi(@Param("uuid") String uuid, @Param("permission") int permission);
}
