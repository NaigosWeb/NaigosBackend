package com.miaoyu.naigos.api.Theme.controller;

import com.miaoyu.naigos.api.Theme.service.ThemeClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/theme/classify")
public class ThemeClassifyController {
    @Autowired
    private ThemeClassifyService themeClassifyService;

    @GetMapping("/all_classify_brief")
    public Map<String, Object> getAllClassifyBriefController() {
        return themeClassifyService.getAllThemeClassifyBriefService();
    }
}
