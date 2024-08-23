package com.miaoyu.naigosbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({
        "com.miaoyu.naigosbackend.bot.mapper",
        "com.miaoyu.naigosbackend.user.mapper"
})
public class NaigosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaigosBackendApplication.class, args);
    }

}
