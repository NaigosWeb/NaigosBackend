package com.miaoyu.naigos.api.Manage.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ManageUserMapper {
    @Select("SELECT * FROM users_users")
    List<UserArchiveModel> getAllUser();
}
