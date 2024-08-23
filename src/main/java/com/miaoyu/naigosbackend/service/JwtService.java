package com.miaoyu.naigosbackend.service;

import com.miaoyu.naigosbackend.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtConfig jwtConfig;

    @Autowired
    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String printAppConfig() {
        System.out.println("jwt key: " + jwtConfig.getKey());
        return null;
    }
    public String getKey(){
        return jwtConfig.getKey();
    }
}
