package com.miaoyu.naigos.api.Theme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miaoyu.naigos.api.Theme.entity.TestRequestEntity;
import com.miaoyu.naigos.api.Theme.service.TestService;
import com.miaoyu.naigos.api.Theme.service.ThemeService;
import com.miaoyu.naigos.model.ThemeModel;
import com.miaoyu.naigos.utils.NeedTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;
    @Autowired
    private NeedTokenUtil needTokenUtil;

    @GetMapping("/all_brief")
    public Map<String, Object> getAllThemeBriefController() {
        return themeService.getAllThemeBriefService();
    }

    @GetMapping("/all_eligible_brief")
    public Map<String, Object> getAllThemeEligibleBriefController(
            @RequestParam("classify_id") String classifyId
    ) {
        return themeService.getAllThemeEligibleBriefService(classifyId);
    }

    @GetMapping("/only")
    public Map<String, Object> getEligibleThemeController(
            @RequestParam("theme_id") String themeId
    ) {
        return themeService.getEligibleThemeService(themeId);
    }

    @PostMapping("/upload")
    public Map<String, Object> uploadThemeController(
            @RequestBody ThemeModel request,
            @RequestHeader("Authorization") String token
    ) {
        Map<String, Object> payload = needTokenUtil.tokenUtil(token, "web");
        if ((int)payload.get("code") == 1){
            return payload;
        }
        return themeService.uploadThemeService(payload.get("data").toString(), request);
    }

    @Autowired
    private TestService testService;
    @PostMapping("/test_json_array")
    public Map<String, Object> toTestJsonArray(@RequestBody TestRequestEntity json) throws JsonProcessingException {
        return testService.addTheme(json.getName(), json.getTagsArray(), "1");
    }
    @GetMapping("/{name}")
    public Map<String, Object> toGetJsonArray(@PathVariable String name) {
        return testService.getTheme(name);
    }
}
