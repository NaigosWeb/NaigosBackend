package com.miaoyu.naigos.api.File.service;

import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.utils.minio.MinioBuckets;
import com.miaoyu.naigos.utils.minio.MinioObjects;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class FileUploadService {
    @Resource
    private MinioClient minioClient;

    @Autowired
    private MinioBuckets minioBuckets;
    @Autowired
    private MinioObjects minioObjects;

    public Map<String, Object> uploadFileByUuidService(String uuid, MultipartFile requestFile) {
        Map<String, Object> bucketOrCreate = minioBuckets.isBucketOrCreate(uuid);
        System.out.println(bucketOrCreate);
        if (!((boolean) bucketOrCreate.get("isBucket"))) {
            return null;
        }
        String s = minioObjects.putObject(uuid, requestFile);
        return new SuccessMap().standardSuccessMap(s);
    }
}
