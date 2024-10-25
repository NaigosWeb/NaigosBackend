package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserPasswordModel;

public interface GetUserPasswordMapper {
    UserPasswordModel getUserPasswordTable(String uuid);
    boolean createUserPasswordRecode(String uuid);
    boolean createUserPasswordRecodeWithPassword(String uuid, String password);
    boolean createUserPasswordRecodeWithCode(String uuid, String code, long expirationDate);
    boolean updateUserPasswordCode(String uuid, String code, long expirationDate);
    boolean checkUserCodeSignin(String uuid);
}
