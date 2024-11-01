package com.miaoyu.naigos.api.Manage.controller;

import com.miaoyu.naigos.api.Manage.service.ManageUserService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/manage/user")
public class ManageUserController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private ManageUserService manageUserService;

    @GetMapping("/all")
    public Map<String, Object> getAllUserController(@RequestHeader("Authorization") String token){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return manageUserService.getAllUserService(payload.get("data").toString());
    }
}
