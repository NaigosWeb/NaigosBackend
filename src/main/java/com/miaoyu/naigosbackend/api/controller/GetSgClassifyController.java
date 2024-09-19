package com.miaoyu.naigosbackend.api.controller;

import com.miaoyu.naigosbackend.api.service.GetSgClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/sgtheme/classify")
@RestController
public class GetSgClassifyController {
    @Autowired
    private GetSgClassifyService getSgClassifyService;

    @GetMapping("/brief")
    public Map<String, Object> getAllBriefSgClassify() {
        return getSgClassifyService.findAllBriefSgClassify();
    }
    @GetMapping("/only")
    public Map<String, Object> getOnlySgClassify(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgClassifyService.findSgClassifyById(classifyId);
    }
}
