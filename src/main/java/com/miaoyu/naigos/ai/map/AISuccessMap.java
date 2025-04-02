package com.miaoyu.naigos.ai.map;

import com.miaoyu.naigos.ai.entity.UserAIEntity;

import java.util.HashMap;
import java.util.Map;

public class AISuccessMap {
    public Map<String, Object> ok(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "AI api success!");
        result.put("data", data);
        return result;
    }
    public Map<String, Object> noSuchUserSuccess() {
        UserAIEntity userAI = new UserAIEntity();
        userAI.setMax_session(10);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "AI api success!");
        result.put("data", userAI);
        return result;
    }
    public Map<String, Object> standardSuccess() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("message", "AI api standard success!");
        result.put("data", "请求成功！");
        return result;
    }
}
