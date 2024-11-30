package com.miaoyu.naigos.api.Theme.controller;

import com.miaoyu.naigos.api.Theme.service.ThemeUDUService;
import com.miaoyu.naigos.model.ThemeModel;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**UDU: Upload Delete Update*/

@RestController
@RequestMapping("/api/theme")
public class ThemeUDUController {
    @Autowired
    private NeedTokenUtil needTokenUtil;
    @Autowired
    private ThemeUDUService themeUDUService;

    @PostMapping("/upload")
    public Map<String, Object> uploadThemeControl(
            @RequestHeader("Authorization") String token,
            @RequestBody ThemeModel request
            ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return themeUDUService.uploadThemeService(payload.get("data").toString(), request);
    }
    @DeleteMapping("/delete")
    public Map<String, Object> deleteThemeControl(
            @RequestHeader("Authorization") String token,
            @RequestParam("theme_id") String themeId
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return themeUDUService.deleteThemeService(payload.get("data").toString(), themeId);
    }
    @PutMapping("/update")
    public Map<String, Object> updateThemeControl(
            @RequestHeader("Authorization") String token,
            @RequestBody ThemeModel request
    ){
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int) payload.get("code") == 1){
            return payload;
        }
        return themeUDUService.updateThemeService(payload.get("data").toString(), request);
    }
    @GetMapping("/all_classify_subcategory")
    public Map<String, Object> getAllClassifyCategoryControl() {
        return themeUDUService.getAllClassifySubcategoryService();
    }
}
