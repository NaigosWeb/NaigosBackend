package com.miaoyu.naigos.api.File.service;

import com.miaoyu.naigos.api.File.entity.FileStorageMaxEntity;
import com.miaoyu.naigos.api.File.mapper.FileStorageMaxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileMaxStorage {
    @Autowired
    private FileStorageMaxMapper fileStorageMaxMapper;

    public long getUserFileMaxStorage(String uuid) {
        FileStorageMaxEntity entity = fileStorageMaxMapper.selectByUuid(uuid);
        if (entity == null) {
            return 209715200;
        }
        if (entity.getTemp_storage_exp() <= System.currentTimeMillis()) {
            return entity.getMax_storage();
        }
        return entity.getMax_storage() + entity.getTemp_storage();
    }
}
