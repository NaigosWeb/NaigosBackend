package com.miaoyu.naigos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan({
        "com.miaoyu.naigos.bot.mapper",
        "com.miaoyu.naigos.user.mapper",
        "com.miaoyu.naigos.api.SgTheme.mapper",
        "com.miaoyu.naigos.api.NaigosNotice.mapper",
        "com.miaoyu.naigos.api.Manage.mapper"
})
public class NaigosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaigosBackendApplication.class, args);
    }

}
