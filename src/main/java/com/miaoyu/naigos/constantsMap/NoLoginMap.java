package com.miaoyu.naigos.constantsMap;

import java.util.HashMap;
import java.util.Map;

public class NoLoginMap {
    public Map<String, Object> noLoginMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", "未登入禁止访问！");
        return data;
    }
}
