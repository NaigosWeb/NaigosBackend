package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.constantsMap.SuccessMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.user.service.OtherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users/other")
public class OtherUserController {
    @Autowired
    private OtherUserService otherUserService;

    @GetMapping("/nickname")
    public Map<String, Object> getOtherNicknameController(@RequestParam String uuid) {
        return otherUserService.getOtherNicknameService(uuid);
    }

    @GetMapping("/archive")
    public Map<String, Object> getOtherArchiveController(@RequestParam String uuid) {
        return otherUserService.getOtherArchiveService(uuid);
    }
}
