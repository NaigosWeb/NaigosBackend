package com.miaoyu.naigosbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NaigosBackendApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(new JwtConfig().getKey());
    }

}
