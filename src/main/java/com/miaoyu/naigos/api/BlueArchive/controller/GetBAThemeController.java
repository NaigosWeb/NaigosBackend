package com.miaoyu.naigos.api.BlueArchive.controller;

import com.miaoyu.naigos.api.BlueArchive.service.GetBAThemeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ba/theme")
public class GetBAThemeController {
    @Autowired
    private GetBAThemeListService getBAThemeListService;

    @GetMapping("/recreate")
    public Map<String, Object> getAllBAThemeBriefByRecreateControl(
            @RequestParam("classify_id") String classifyId
    ) {
        return getBAThemeListService.getAllThemeByRecreate(classifyId);
    }

    @GetMapping("/record")
    public Map<String, Object> getAllBAThemeBriefByRecordControl(@RequestParam("classify_id") String classifyId) {
        return getBAThemeListService.getAllThemeByRecord(classifyId);
    }
}
