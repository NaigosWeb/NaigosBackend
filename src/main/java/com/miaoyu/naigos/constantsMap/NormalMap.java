package com.miaoyu.naigos.constantsMap;

import java.util.HashMap;
import java.util.Map;

public class NormalMap {
    public Map<String, Object> normalSuccessMap(Object target) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("message", "ok");
        data.put("data", target);
        return data;
    }
}
