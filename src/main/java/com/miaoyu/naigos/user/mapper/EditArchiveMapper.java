package com.miaoyu.naigos.user.mapper;

public interface EditArchiveMapper {
    boolean editArchiveByUuid (String uuid, String nickname, String email, Integer qqId, String city);
}
