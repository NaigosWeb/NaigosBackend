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
    public Map<String, Object> noSuchArchive(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", "档案未找到！");
        return data;
    }
    public Map<String, Object> apiFetchError(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", "接口请求出错！");
        return data;
    }
    public Map<String, Object> resourceNotExist(){
        Map<String, Object> data = new HashMap<>();
        data.put("code", 1);
        data.put("message", "资源不存在！The resource does not exist!");
        return data;
    }
}
