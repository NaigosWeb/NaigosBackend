package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/users/checkin")
@RestController
public class UserCheckinController {
    @Autowired
    private NeedTokenUtil needTokenUtil;

    @GetMapping("/web")
    public Map<String, Object> webCheckin(@RequestHeader("Authorization") String token) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        String uuid = (String) payload.get("data");

    }
}
