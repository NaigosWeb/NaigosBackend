package com.miaoyu.naigos.api.NaigosNotice.controller;

import com.miaoyu.naigos.api.NaigosNotice.service.DeleteNoticeService;
import com.miaoyu.naigos.api.NaigosNotice.service.GetNoticeService;
import com.miaoyu.naigos.api.NaigosNotice.service.UpdateNoticeService;
import com.miaoyu.naigos.api.NaigosNotice.service.UploadNoticeService;
import com.miaoyu.naigos.model.NaigosNoticeModel;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notice")
public class NaigosNoticeController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private GetNoticeService getNoticeService;
    @Autowired
    private UploadNoticeService uploadNoticeService;
    @Autowired
    private UpdateNoticeService updateNoticeService;
    @Autowired
    private DeleteNoticeService deleteNoticeService;

    @GetMapping("/all")
    public Map<String, Object> getAllNoticeController(){
        return getNoticeService.getAllNoticeService();
    }
    @GetMapping("/only")
    public Map<String, Object> getNoticeByIdController(@RequestParam("notice_id") String noticeId){
        return getNoticeService.getNoticeByIdService(noticeId);
    }
    @PostMapping("/upload")
    public Map<String, Object> uploadNoticeController(
            @RequestHeader("Authorization") String token,
            @RequestBody NaigosNoticeModel request
            ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return uploadNoticeService.uploadNoticeService(payload.get("data").toString(), request);
    }
    @PutMapping("/update")
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
    @DeleteMapping("/delete")
    public Map<String, Object> deleteNoticeController(
            @RequestHeader("Authorization") String token,
            @RequestParam("notice_id") String noticeId
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return deleteNoticeService.deleteNoticeService(payload.get("data").toString(), noticeId);
    }
}
