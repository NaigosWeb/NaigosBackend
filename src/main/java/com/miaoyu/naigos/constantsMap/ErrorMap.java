package com.miaoyu.naigos.constantsMap;

import java.util.HashMap;
import java.util.Map;

public class ErrorMap {
    public Map<String, Object> errorMap(String target){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", target);
        return data;
    }
    public Map<String, Object> insufficientAccountPermissions(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", "账号权限不足！Insufficient Permissions");
        return data;
    }
    
}
