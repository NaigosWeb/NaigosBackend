package com.miaoyu.naigos.api.SgTheme.controller;

import com.miaoyu.naigos.api.SgTheme.service.GetSgClassifyService;
import com.miaoyu.naigos.api.SgTheme.service.GetSgService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sgtheme")
public class GetSgController {
    @Autowired
    private GetSgService getSgService;

    /**
     * 获取所有搜狗输入法皮肤全部信息
     * @return Map->JSON
     * */
    @GetMapping("/all")
    public Map<String, Object> getAllSgTheme() {
        return getSgService.findAllSg();
    }
    /**
     * 获取所有搜狗输入法皮肤简略信息
     * @return Map->JSON
     * */
    @GetMapping("/all_brief")
    public Map<String, Object> getAllBriefSgTheme() {
        return getSgService.findAllBriefSg();
    }
    /**
     * 根据分类ID获取所有对应搜狗输入法的简略信息
     * @param classifyId 搜狗输入法的皮肤分类ID
     * @return Map->JSON*/
    @GetMapping("/all_eligible_brief")
    public Map<String, Object> getAllAppointBriefSgTheme(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgService.findAllBriefSgByAppoint(classifyId);
    }
    /**
     * 根据搜狗输入法皮肤ID专一获得该皮肤详情信息
     * @param themeId 搜狗输入法的皮肤ID
     * @return Map->JSON*/
    @GetMapping("/only")
    public Map<String, Object> getOnlySgTheme(
            @RequestParam(value = "theme_id") String themeId
            ) {
        return getSgService.findSgById(themeId);
    }

    @Autowired
    private GetSgClassifyService getSgClassifyService;

    /**
     * 获取所有搜狗输入法分类的简略信息
     * @return Map->JSON*/
    @GetMapping("/all_classify_brief")
    public Map<String, Object> getAllBriefSgClassify() {
        return getSgClassifyService.findAllBriefSgClassify();
    }
    /// 根据分类ID专一获取该分类的详情信息
    /**
     * 根据分类ID专一获取该分类的详情信息
     * @param classifyId 搜狗输入法的皮肤的分类ID
     * @return Map->JSON*/
    @GetMapping("/classify_only")
    public Map<String, Object> getOnlySgClassify(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgClassifyService.findSgClassifyById(classifyId);
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
