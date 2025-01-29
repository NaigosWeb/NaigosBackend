package com.miaoyu.naigos.api.Sundry.controller;

import com.miaoyu.naigos.constantsMap.SuccessMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sundry")
public class GetServerTimeController {
    @GetMapping("/get_server_time")
    public Map<String, Object> getServerTime() {
        return new SuccessMap().standardSuccessMap(System.currentTimeMillis());
    }
}
