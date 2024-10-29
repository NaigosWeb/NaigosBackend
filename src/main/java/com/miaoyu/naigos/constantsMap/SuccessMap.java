package com.miaoyu.naigos.constantsMap;

import java.util.HashMap;
import java.util.Map;

public class SuccessMap {
    public Map<String, Object> standardSuccessMap(Object target) {
        Map<String, Object> data  = new HashMap<>();
        data.put("code", 0);
        data.put("message", "Standard success!");
        data.put("data", target);
        return data;
    }
}
