package com.miaoyu.naigosbackend.jwtHandle;

import com.miaoyu.naigosbackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtSigned {
    @Autowired
    private JwtService jwtService;

    public Map<String, Object> jwtSigned(String tokenRule, String[] args){

    }
}
