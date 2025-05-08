/*
* 签发JWT的类
* param
*   tokenRule来源（token的签发源）
*   target签发接收方的账号*/
package com.miaoyu.naigos.jwtHandle;

import com.miaoyu.naigos.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtSigned {
    @Autowired
    private JwtService jwtService;

    public String jwtSigned(String tokenRule, String target){
        String issuer = null;
        String targetIdName = "";
        if (tokenRule.equals("web")){
            issuer = "com.miaoyu.naigos.network.service";
            targetIdName = "uuid";
        } else if (tokenRule.equals("bot")) {
            issuer = "com.miaoyu.naigos.bot.service";
            targetIdName = "appid";
        }
        String jwtServiceKey = jwtService.getKey();
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtServiceKey));
        Map<String, Object> claims = new HashMap<>();

        claims.put(targetIdName, target);
        claims.put("source", tokenRule);
        claims.put("service", "naigos");
        return Jwts.builder()
                .signWith(key, Jwts.SIG.HS256)
                .issuer(issuer)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .claims(claims)
                .compact();
    }
}
