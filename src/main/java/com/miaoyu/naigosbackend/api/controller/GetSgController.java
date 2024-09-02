package com.miaoyu.naigosbackend.api.controller;

import com.miaoyu.naigosbackend.api.entity.SgBriefEntity;
import com.miaoyu.naigosbackend.api.service.GetSgService;
import com.miaoyu.naigosbackend.model.SogouInputThemeModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sgtheme")
public class GetSgController {
    @Autowired
    private GetSgService getSgService;

    @GetMapping("/all")
    public List<SogouInputThemeModel> getAllSgTheme(HttpServletRequest request) {
        System.out.println(IpUtils.getIpAddress(request));
        return getSgService.findAllSg();
    }
    @GetMapping("/brief/all")
    public List<SgBriefEntity> getAllBriefSgTheme() {
        return getSgService.findAllBriefSg();
    }
    @GetMapping("/brief/appoint")
    public List<SgBriefEntity> getAllAppointBriefSgTheme(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgService.findAllBriefSgByAppoint(classifyId);
    }
    @GetMapping("/only")
    public SogouInputThemeModel getOnlySgTheme(
            @RequestParam(value = "theme_id") String themeId
            ) {
        return getSgService.findSgById(themeId);
    }
}

class IpUtils {

    /**
     * 获取访问者的真实IP地址
     *
     * @param request HttpServletRequest对象
     * @return 访问者的真实IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP值之间以逗号分隔
        if (ip != null && ip.length() > 15) { // "XXX.XXX.XXX.XXX".length() = 15
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
