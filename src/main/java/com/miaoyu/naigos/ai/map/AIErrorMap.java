package com.miaoyu.naigos.ai.map;

import java.util.HashMap;
import java.util.Map;

public class AIErrorMap {
    public Map<String, Object> no(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("message", "错误问题！error!");
        result.put("data", data);
        return result;
    }
    public Map<String, Object> noSuchUserAI () {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 101);
        result.put("message", "找不到用户！No such user");
        return result;
    }
    public Map<String, Object> insufficientBalance() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "布丁余量不足！Pudding insufficient balance!");
        return result;
    }
    public Map<String, Object> recordError () {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 201);
        result.put("message", "记录失败！Record Error");
        return result;
    }
}
