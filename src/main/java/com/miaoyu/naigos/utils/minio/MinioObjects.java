package com.miaoyu.naigos.utils.minio;

import com.miaoyu.naigos.utils.HumanFileSize;
import io.minio.*;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MinioObjects {
    @Resource
    private MinioClient minioClient;
    @Autowired
    private MinioBuckets minioBuckets;

    private static final Tika tika = new Tika();

    public String putObject(String uuid, MultipartFile requestFile) {
        String lowUuid = uuid.toLowerCase();
        String filename = requestFile.getOriginalFilename();
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(requestFile.getBytes());
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(lowUuid)
                    .stream(inputStream, requestFile.getSize(), -1)
                    .contentType(tika.detect(requestFile.getInputStream()))
                    .object(filename).build());
            if (objectWriteResponse == null){
                return null;
            }
            return "https://file.naigos.cn:52011/" + lowUuid + "/" + filename;
        } catch (Exception e){
            return null;
        }
    }
    public List<Map<String, Object>> getObjectList(String uuid) {
        String lowUuid = uuid.toLowerCase();
        if (!minioBuckets.isBucket(lowUuid)) {
            return null;
        }
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(lowUuid).build());
        List<Map<String, Object>> list = new ArrayList<>();
        results.forEach(itemResult -> {
            try {
                Item item = itemResult.get();
                Map<String, Object> map = new HashMap<>();
                map.put("url", "https://file.naigos.cn:52011/" + lowUuid + "/" + item.objectName());
                map.put("size", item.size());
                map.put("size_text", new HumanFileSize().humanFileSize(item.size()));
                list.add(map);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return list;
    }
}
