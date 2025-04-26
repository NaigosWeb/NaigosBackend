package com.miaoyu.naigos.blueArchive.controller;

import com.miaoyu.naigos.blueArchive.service.BaFirstClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ba/first_classify")
public class BaFirstClassifyController {
    @Autowired
    private BaFirstClassifyService baFirstClassifyService;

    /**获取全部一级分类实体
     * @return List类型中包含所有BA的一级分类实体*/
    @GetMapping("/all")
    public Map<String, Object> getBaAllFirstClassifyControl() {
        return baFirstClassifyService.getBaAllFirstClassifyService();
    }
}
