package com.miaoyu.naigosbackend.user.mapper;

import com.miaoyu.naigosbackend.model.UserPasswordModel;

public interface IsUserMapper {
    boolean isUserInDatabaseByUniqueId(Integer uniqueId);
    boolean isUserInDatabaseByEmail(String email);
    UserPasswordModel isUserPasswordInDatabase(String uuid);
}
