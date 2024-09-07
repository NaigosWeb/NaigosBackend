package com.miaoyu.naigosbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允许所有路径
//                        .allowedOrigins("http://127.0.0.1:5173") // 允许访问的源
                        .allowedOriginPatterns ("*")
                        .allowedMethods("*") // 允许的方法
                        .allowedHeaders("*") // 允许的头
                        .allowCredentials(true); // 允许发送Cookie
            }
        };
    }
}