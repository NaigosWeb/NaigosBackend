package com.miaoyu.naigos.api.SgTheme.controller;

import com.miaoyu.naigos.api.SgTheme.service.BARecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/blue_archive/record")
public class BARecordController {
    @Autowired
    private BARecordService baRecordService;

    @GetMapping("/classify/all_brief")
    public Map<String, Object> getAllRecordClassifyController(){
        return baRecordService.getAllClassifyBriefService();
    }
    @GetMapping("/classify/only")
    public Map<String, Object> getOnlyRecordClassifyController(
            @RequestParam(value = "classify_id") String classifyId
    ){
        return baRecordService.getOnlyClassifyService(classifyId);
    }
}
