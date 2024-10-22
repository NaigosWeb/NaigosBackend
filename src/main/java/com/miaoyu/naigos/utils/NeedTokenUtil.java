package com.miaoyu.naigos.utils;

import com.miaoyu.naigos.constantsMap.NoLoginMap;
import com.miaoyu.naigos.jwtHandle.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NeedTokenUtil {
    @Autowired
    private JwtParser jwtParser;

    public Map<String, Object> tokenUtil (String token, String source){
        if (token == null){
            return new NoLoginMap().noLoginMap();
        }
        return jwtParser.jwtParser(token, source);
    }
}
