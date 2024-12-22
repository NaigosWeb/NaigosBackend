package com.miaoyu.naigos.api.File.controller;

import com.miaoyu.naigos.api.File.service.FileUploadService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {
    @Resource
    private NeedTokenUtil needTokenUtil;
    @Resource
    private MinioClient minioClient;
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return ResponseEntity.status(HttpStatus.OK).body(payload);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                fileUploadService.uploadFileByUuidService(payload.get("data").toString(), file));
    }
}
