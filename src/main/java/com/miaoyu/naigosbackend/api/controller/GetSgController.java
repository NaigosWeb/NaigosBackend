package com.miaoyu.naigosbackend.api.controller;

import com.miaoyu.naigosbackend.api.service.GetSgService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sgtheme")
public class GetSgController {
    @Autowired
    private GetSgService getSgService;

    @GetMapping("/all")
    public Map<String, Object> getAllSgTheme(HttpServletRequest request) {
        return getSgService.findAllSg();
    }
    @GetMapping("/brief/all")
    public Map<String, Object> getAllBriefSgTheme() {
        return getSgService.findAllBriefSg();
    }
    @GetMapping("/brief/appoint")
    public Map<String, Object> getAllAppointBriefSgTheme(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgService.findAllBriefSgByAppoint(classifyId);
    }
    @GetMapping("/only")
    public Map<String, Object> getOnlySgTheme(
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
