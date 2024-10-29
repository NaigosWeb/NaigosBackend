package com.miaoyu.naigos.api.Notice.controller;

import com.miaoyu.naigos.api.Notice.service.UpdateNoticeService;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.userPermi.GetUserPermiFromDB;
import com.miaoyu.naigos.userPermi.PermiConst;
import com.miaoyu.naigos.userPermi.UserPermi;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class UpdateNoticeController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private UpdateNoticeService updateNoticeService;

    @PostMapping("/update")
    public Map<String, Object> updateNoticeController(
            @RequestHeader("Authorization") String token,
            @RequestBody NaigosNoticeModel request
            ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return updateNoticeService.updateNoticeService(payload.get("data").toString(), request);
    }
}
