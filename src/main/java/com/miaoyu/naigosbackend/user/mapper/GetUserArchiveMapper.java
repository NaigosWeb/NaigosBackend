package com.miaoyu.naigosbackend.user.mapper;

import com.miaoyu.naigosbackend.model.UserArchiveModel;

public interface GetUserArchiveMapper {
    UserArchiveModel getUserArchiveByEmail(String email);
    UserArchiveModel getUserArchiveByUuid(String uuid);
    UserArchiveModel getUserArchiveByUniqueId(Integer uniqueId);
}
