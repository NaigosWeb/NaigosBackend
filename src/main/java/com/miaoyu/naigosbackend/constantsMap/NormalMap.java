package com.miaoyu.naigosbackend.constantsMap;

import java.util.HashMap;
import java.util.Map;

public class NormalMap {
    public Map<String, Object> normalSuccessMap(Object target) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("code", 0);
        data.put("message", "success");
        data.put("data", target);
        return data;
    }
}
