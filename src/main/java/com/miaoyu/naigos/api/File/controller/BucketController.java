package com.miaoyu.naigos.api.File.controller;

import com.miaoyu.naigos.api.File.service.FileBucketService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/file/bucket")
public class BucketController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private FileBucketService fileBucketService;

    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> getBucketControl(
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return ResponseEntity.status(HttpStatus.OK).body(payload);
        }
        return ResponseEntity.status(HttpStatus.OK).body(fileBucketService.getBucketService(payload.get("data").toString()));
    }
}
