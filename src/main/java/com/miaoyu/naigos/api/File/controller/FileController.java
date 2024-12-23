package com.miaoyu.naigos.api.File.controller;

import com.miaoyu.naigos.api.File.service.FileService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Resource
    private NeedTokenUtil needTokenUtil;
    @Resource
    private MinioClient minioClient;
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFileControl(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return ResponseEntity.status(HttpStatus.OK).body(payload);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                fileService.uploadFileByUuidService(payload.get("data").toString(), file));
    }
    @GetMapping("/objects")
    public ResponseEntity<Map<String, Object>> getObjectsListControl(
            @RequestHeader("Authorization") String token
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return ResponseEntity.status(HttpStatus.OK).body(payload);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                fileService.getObjectsListService(payload.get("data").toString())
        );
    }
}
