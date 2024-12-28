package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.user.service.EditArchiveService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/users/edit_archive")
public class EditArchiveController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private EditArchiveService editArchiveService;

    @PutMapping("/web")
    public Map<String, Object> editArchiveController(
            @RequestHeader("Authorization") String token,
            @RequestBody UserArchiveModel request
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int)payload.get("code") == 1){
            return payload;
        }
        return editArchiveService.editArchiveService((String) payload.get("data"), request);
    }
    @PostMapping("/avatar")
    public Map<String, Object> editAvatarControl(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String token
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int)payload.get("code") == 1){
            return payload;
        }
        return editArchiveService.editAvatarService(payload.get("data").toString(), file);
    }
}
