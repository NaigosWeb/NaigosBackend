package com.miaoyu.naigos.user.mapper;

public interface EditArchiveMapper {
    boolean editArchiveByUuid (String uuid, String nickname, String email, Long qqId, String city);
    boolean editAvatarByUuid(String uuid, String avatar);
}
