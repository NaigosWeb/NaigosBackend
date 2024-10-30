package com.miaoyu.naigos.api.Weather.controller;

import com.miaoyu.naigos.api.Weather.service.GetWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class GetWeatherController {
    @Autowired
    private GetWeatherService getWeatherService;

    @GetMapping("/get")
    public Map<String, Object> getWeatherController(@RequestParam("city") String city) {
        return getWeatherService.getWeatherService(city);
    }
}
