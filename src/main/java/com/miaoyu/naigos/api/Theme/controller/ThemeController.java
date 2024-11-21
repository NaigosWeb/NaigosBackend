package com.miaoyu.naigos.api.Theme.controller;

import com.miaoyu.naigos.api.Theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

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
}
