package com.miaoyu.naigos.user.controller;

import com.miaoyu.naigos.constantsMap.NoLoginMap;
import com.miaoyu.naigos.constantsMap.NormalMap;
import com.miaoyu.naigos.jwtHandle.JwtParser;
import com.miaoyu.naigos.user.service.GetUserArchiveService;
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

    /**
     * 获取用户的详情信息
     * param
     *  token: String 有效的令牌*/
    @GetMapping("/current")
    public Map<String, Object> getCurrentUser(@RequestHeader("Authorization") String token) {
        if (token == null) {
            return new NoLoginMap().noLoginMap();
        }
        System.out.println(token);
        Map<String, Object> payload = jwtParser.jwtParser(token, "web");
        System.out.println(payload);
        if ((int) payload.get("code") == 1) {
            return payload;
        }
        return new NormalMap().normalSuccessMap(
                getUserArchiveService.getUserArchive(2, (String) payload.get("data"))
        );
    }
}
