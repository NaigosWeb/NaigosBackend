package com.miaoyu.naigos.api.BlueArchive.controller;

import com.miaoyu.naigos.api.BlueArchive.service.GetBASgthemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ba/sgtheme")
public class GetBASgthemeController {
    @Autowired private GetBASgthemeService getBASgthemeService;
    @GetMapping("/all_brief_record")
    public Map<String, Object> getAllBASgthemeBriefByRecordControl() {
        return getBASgthemeService.getAllBASgthemeBriefByRecordService();
    }
    @GetMapping("/all_brief_recreate")
    public Map<String, Object> getAllBASgthemeBriefByRecreateControl() {
        return getBASgthemeService.getAllBASgthemeBriefByRecreateService();
    }
}
