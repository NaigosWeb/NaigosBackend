package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.model.UserArchiveModel;

public interface GetUserArchiveMapper {
    UserArchiveModel getUserArchiveByEmail(String email);
    UserArchiveModel getUserArchiveByUuid(String uuid);
    UserArchiveModel getUserArchiveByUniqueId(Integer uniqueId);
}
