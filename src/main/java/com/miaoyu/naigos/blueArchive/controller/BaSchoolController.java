package com.miaoyu.naigos.blueArchive.controller;

import com.miaoyu.naigos.blueArchive.model.BaSchoolModel;
import com.miaoyu.naigos.blueArchive.service.BaSchoolService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ba/school")
public class BaSchoolController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BaSchoolService baSchoolService;

    /**获取所有school实体
     * @return List类型包含所有school实体*/
    @GetMapping("/all")
    public Map<String, Object> getBaAllSchoolControl() {
        return baSchoolService.getBaAllSchoolService();
    }

    /**
     * 根据school_id获得唯一符合的school实体
     * @param schoolId school的id
     * @return 唯一的school实体*/
    @GetMapping("only")
    public Map<String, Object> getBaOnlySchoolControl(
            @RequestParam("school_id") String schoolId
    ) {
        return baSchoolService.getBaSchoolByIdService(schoolId);
    }

    /**上传school参数
     * @param token 管理者以上权限者的令牌
     * @param upType 上传方式"upload"/"update"
     * @param request school的实体
     * @return 修改是否正确完成*/
    @PostMapping("/up_school")
    public Map<String, Object> upBaSchoolControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("up_type") String upType,
            @RequestBody BaSchoolModel request
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return baSchoolService.upBaSchoolService(payload.get("data").toString(), upType, request);
    }
}
