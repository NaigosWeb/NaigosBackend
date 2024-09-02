package com.miaoyu.naigosbackend.api.controller;

import com.miaoyu.naigosbackend.api.entity.SgClassifyBriefEntity;
import com.miaoyu.naigosbackend.api.service.GetSgClassifyService;
import com.miaoyu.naigosbackend.model.SogouInputThemeClassifyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/sgtheme/classify")
@RestController
public class GetSgClassifyController {
    @Autowired
    private GetSgClassifyService getSgClassifyService;

    @GetMapping("/brief")
    public List<SgClassifyBriefEntity> getAllBriefSgClassify() {
        return getSgClassifyService.findAllBriefSgClassify();
    }
    @GetMapping("/only")
    public SogouInputThemeClassifyModel getOnlySgClassify(
            @RequestParam(value = "classify_id") String classifyId
    ) {
        return getSgClassifyService.findSgClassifyById(classifyId);
    }
}
