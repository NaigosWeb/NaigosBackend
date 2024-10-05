package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserPasswordModel;

public interface IsUserMapper {
    boolean isUserInDatabaseByUniqueId(Integer uniqueId);
    boolean isUserInDatabaseByEmail(String email);
    UserPasswordModel isUserPasswordInDatabase(String uuid);
}
