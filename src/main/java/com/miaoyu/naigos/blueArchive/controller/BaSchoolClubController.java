package com.miaoyu.naigos.blueArchive.controller;

import com.miaoyu.naigos.blueArchive.model.BaSchoolClubModel;
import com.miaoyu.naigos.blueArchive.service.BaSchoolClubService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ba/school/club")
public class BaSchoolClubController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private BaSchoolClubService baSchoolClubService;

    /**获取BA中全部的club实体
     * @return List类型中包含BA中全部的club实体*/
    @GetMapping("/all")
    public Map<String, Object> getBaAllClubControl() {
        return baSchoolClubService.getBaAllClubService();
    }

    /**根据school_id获取BA中全部符合条件的club实体
     * @param schoolId school的id
     * @return List类型中包含BA中符合school_id条件的全部club实体*/
    @GetMapping("/clubs_by_school")
    public Map<String, Object> getBaClubsBySchoolControl(
            @RequestParam("school_id") String schoolId
    ) {
        return baSchoolClubService.getBaClubsBySchoolIdService(schoolId);
    }

    /**根据club_id获取符合条件的唯一club实体
     * @param clubId club的id
     * @return 符合条件的唯一club实体*/
    @GetMapping("/only")
    public Map<String, Object> getBaClubOnlyControl(
            @RequestParam("club_id") String clubId
    ) {
        return null;
    }

    /**上传club参数
     * @param token 管理者以上权限者的令牌
     * @param upType 上传方式"upload"/"update"
     * @param request club的实体
     * @return 修改是否正确完成*/
    @PostMapping("/up_club")
    public Map<String, Object> upBaSchoolClubControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("up_type") String upType,
            @RequestBody BaSchoolClubModel request
            ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return baSchoolClubService.upBaClubService(payload.get("data").toString(), upType, request);
    }
}
