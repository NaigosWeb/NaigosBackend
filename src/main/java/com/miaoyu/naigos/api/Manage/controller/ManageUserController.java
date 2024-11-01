package com.miaoyu.naigos.api.Manage.controller;

import com.miaoyu.naigos.api.Manage.service.ManageUserService;
import com.miaoyu.naigos.model.UserArchiveModel;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/edit")
    public Map<String, Object> editUserController(
            @RequestHeader("Authorization") String token,
            @RequestBody UserArchiveModel request
            ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return manageUserService.editUserService(payload.get("data").toString(), request);
    }
    @PostMapping("/change_permi")
    public Map<String, Object> changePermiController(
            @RequestHeader("Authorization") String token,
            @RequestParam("modified_uuid") String modifiedUuid,
            @RequestParam("permission") int permission
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return manageUserService.changePermiService(payload.get("data").toString(), modifiedUuid, permission);
    }
}
