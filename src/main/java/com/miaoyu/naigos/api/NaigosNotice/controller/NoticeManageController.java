package com.miaoyu.naigos.api.NaigosNotice.controller;

import com.miaoyu.naigos.api.NaigosNotice.service.GetNoticeService;
import com.miaoyu.naigos.constantsMap.ErrorMap;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/notice/manage")
public class NoticeManageController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private UserPermi userPermi;
    @Autowired
    private GetUserPermiFromDB getUserPermiFromDB;
    @Autowired
    private GetNoticeService getNoticeService;

    @GetMapping("/all")
    public Map<String, Object> adminGetAllNoticeController(
            @RequestHeader("Authorization") String token
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int)payload.get("code") == 1){
            return payload;
        }
        userPermi.setPermissions(getUserPermiFromDB.getUserPermiByUuidService(payload.get("data").toString()));
        if (userPermi.hasPermission(PermiConst.ADMIN)){
            return getNoticeService.getAllNoticeService();
        } else if (userPermi.hasPermission(PermiConst.MANAGER)) {
            return getNoticeService.getAllNoticeByUuidService(payload.get("data").toString());
        }
        return new ErrorMap().insufficientAccountPermissions();
    }
}
