package com.miaoyu.naigos.api.File.service;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.utils.minio.MinioBuckets;
import com.miaoyu.naigos.utils.minio.MinioObjects;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class FileService {
    @Resource
    private MinioClient minioClient;

    @Autowired
    private MinioBuckets minioBuckets;
    @Autowired
    private MinioObjects minioObjects;

    public Map<String, Object> uploadFileByUuidService(String uuid, MultipartFile requestFile) {
        Map<String, Object> bucketOrCreate = minioBuckets.isBucketOrCreate(uuid);
        if (!((boolean) bucketOrCreate.get("isBucket"))) {
            return new ErrorMap().resourceNotExist();
        }
        String s = minioObjects.putObject(uuid, requestFile);
        if (s == null) {
            return new ErrorMap().uploadUpdateDeleteErrorMap(0);
        }
        return new SuccessMap().standardSuccessMap(s);
    }

    public Map<String, Object> getObjectsListService(String uuid) {
        List<Map<String, Object>> objectList = minioObjects.getObjectList(uuid);
        if (objectList == null) {
            return new ErrorMap().resourceNotExist();
        }
        return new SuccessMap().standardSuccessMap(objectList);
    }
}
