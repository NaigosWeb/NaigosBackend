package com.miaoyu.naigos.api.File.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.utils.HumanFileSize;
import com.miaoyu.naigos.utils.minio.MinioBuckets;
import com.miaoyu.naigos.utils.minio.MinioObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileBucketService {
    @Autowired
    private MinioBuckets minioBuckets;
    @Autowired
    private MinioObjects minioObjects;
    @Autowired
    private FileMaxStorage fileMaxStorage;

    public Map<String, Object> getBucketService(String uuid){
        List<Map<String, Object>> objectList = minioObjects.getObjectList(uuid);
        if (objectList == null) {
            return new ErrorMap().resourceNotExist();
        }
        long maxStorage = fileMaxStorage.getUserFileMaxStorage(uuid);
        long size = 0;
        for (Map<String, Object> object : objectList) {
            size += (long)object.get("size");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("bucket", uuid.toLowerCase());
        result.put("size", size);
        result.put("size_text", new HumanFileSize().humanFileSize(size));
        result.put("max_size", maxStorage);
        result.put("max_size_text", new HumanFileSize().humanFileSize(maxStorage));
        return new SuccessMap().standardSuccessMap(result);
    }
}
