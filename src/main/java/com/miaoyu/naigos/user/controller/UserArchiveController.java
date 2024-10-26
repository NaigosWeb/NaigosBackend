package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.constantsMap.NoLoginMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.jwtHandle.JwtParser;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users/archive")
public class UserArchiveController {
    @Autowired
    private GetUserArchiveService getUserArchiveService;

    @Autowired
    private JwtParser jwtParser;
    @Autowired
    private NeedTokenUtil needTokenUtil;

    /**
     * 获取用户的详情信息
     * @param token 有效的令牌
     * @return Map->Json
     * */
    @GetMapping("/current")
    public Map<String, Object> getCurrentUser(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return new NoLoginMap().noLoginMap();
        }
        Map<String, Object> payload = jwtParser.jwtParser(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return new NormalMap().normalSuccessMap(
                getUserArchiveService.getUserArchive(2, (String) payload.get("data"))
        );
    }
    @GetMapping("me_score")
    public Map<String, Object> getMeScore(@RequestHeader("Authorization") String token) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        String uuid = (String) payload.get("data");
        return getUserArchiveService.getMeScoreService(uuid);
    }
    /**
     * 获取用户的头像地址
     * @param token 有效的令牌
     * @return Map->JSON
     * */
    @GetMapping("/me_avatar")
    public Map<String, Object> getMeAvatar(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return new NoLoginMap().noLoginMap();
        }
        Map<String, Object> payload = jwtParser.jwtParser(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        String uuid = (String) payload.get("data");
        return getUserArchiveService.getMeAvatarService(uuid);
    }

    @GetMapping("/me_permi")
    public Map<String, Object> getMePermi(@RequestHeader("Authorization") String token) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return getUserArchiveService.getMePermissionService((String) payload.get("data"));
    }
    @GetMapping("/me_permi_list")
    public Map<String, Object> getMePermiList(@RequestHeader("Authorization") String token) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return getUserArchiveService.getMePermissionServiceList((String) payload.get("data"));
    }
}
