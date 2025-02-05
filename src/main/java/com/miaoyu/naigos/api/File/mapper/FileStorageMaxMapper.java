package com.miaoyu.naigos.api.File.mapper;

import com.miaoyu.naigos.api.File.entity.FileStorageMaxEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface FileStorageMaxMapper {
    @Select("SELECT * FROM users_storage WHERE uuid = #{uuid}")
    FileStorageMaxEntity selectByUuid(@Param("uuid") String uuid);
}
