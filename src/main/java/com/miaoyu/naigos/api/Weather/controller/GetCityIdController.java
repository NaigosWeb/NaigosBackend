package com.miaoyu.naigos.api.Weather.controller;

import com.miaoyu.naigos.api.Weather.service.GetCityIdService;
import com.miaoyu.naigos.constantsMap.NormalMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class GetCityIdController {

    @Autowired
    private GetCityIdService getCityIdService;

    @GetMapping("/city_id")
    public Map<String, Object> getCityId (
            @RequestParam(value = "city_name") String cityName
    ) {
        String cityId = getCityIdService.getCityIdService(cityName);
        return new NormalMap().normalSuccessMap(cityId);
    }
}
