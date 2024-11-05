package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.user.service.UserServiceScopeService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users/service_scope")
public class UserServiceScopeController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private UserServiceScopeService userServiceScopeService;

    @GetMapping("/me")
    public Map<String, Object> getMeServiceScopeController(
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return userServiceScopeService.getMeServiceScopeService(payload.get("data").toString());
    }
}
