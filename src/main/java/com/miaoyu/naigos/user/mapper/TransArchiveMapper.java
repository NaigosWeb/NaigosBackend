package com.miaoyu.naigos.user.mapper;

import com.miaoyu.naigos.user.entity.OldArchiveEntity;

public interface TransArchiveMapper {
    OldArchiveEntity getOldArchiveByQqId(String qqId);
}
