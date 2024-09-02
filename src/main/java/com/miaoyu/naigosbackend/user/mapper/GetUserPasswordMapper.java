package com.miaoyu.naigosbackend.user.mapper;

import com.miaoyu.naigosbackend.model.UserPasswordModel;

public interface GetUserPasswordMapper {
    UserPasswordModel getUserPasswordTable(String uuid);
    boolean createUserPasswordRecode(String uuid);
    boolean createUserPasswordRecodeWithCode(String uuid, String code, long expirationDate);
    boolean updateUserPasswordCode(String uuid, String code, long expirationDate);
    boolean checkUserCodeSignin(String uuid);
}
