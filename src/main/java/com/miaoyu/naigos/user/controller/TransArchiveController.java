package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.user.service.TransArchiveService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users/trans_archive")
public class TransArchiveController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private TransArchiveService transArchiveService;

    @GetMapping("/web")
    public Map<String, Object> transArchive(@RequestHeader("Authorization") String token) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int)payload.get("code") == 1){
            return payload;
        }
        return transArchiveService.transArchiveService((String) payload.get("data"));
    }
}
