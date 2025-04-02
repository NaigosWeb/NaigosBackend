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
        "com.miaoyu.naigos.api.Manage.mapper",
        "com.miaoyu.naigos.api.Theme.mapper",
        "com.miaoyu.naigos.api.BlueArchive.mapper",
        "com.miaoyu.naigos.api.Blog.mapper",
        "com.miaoyu.naigos.api.File.mapper",
        "com.miaoyu.naigos.ai.mapper"
})
public class NaigosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaigosBackendApplication.class, args);
    }

}
