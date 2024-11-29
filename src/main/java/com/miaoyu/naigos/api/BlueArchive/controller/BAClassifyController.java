package com.miaoyu.naigos.api.BlueArchive.controller;

import com.miaoyu.naigos.api.BlueArchive.service.BAClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ba/classify")
public class BAClassifyController {
    @Autowired private BAClassifyService BAClassifyService;
    @GetMapping("/all_brief_recreate")
    public Map<String, Object> getAllBAClassifyBriefControl() {
        return BAClassifyService.getAllBAClassifyBriefService();
    }
    @GetMapping("/eligible_recreate")
    public Map<String, Object> getEligibleBAClassifyControl(
            @RequestParam("classify_id") String classifyId
    ) {
        return BAClassifyService.getEligibleBAClassify(classifyId);
    }
}
