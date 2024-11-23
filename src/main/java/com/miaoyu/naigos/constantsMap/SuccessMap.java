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
    public Map<String, Object> uploadOrUpdateSuccessMap(int upType) {
        Map<String, Object> data  = new HashMap<>();
        data.put("code", 0);
        data.put("message", "Upload or Update success!");
        switch (upType) {
            case 0: data.put("data", "上传成功！"); break;
            case 1: data.put("data", "更新成功！"); break;
            default: break;
        }
        return data;
    }
}
