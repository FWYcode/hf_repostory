package com.hf.game.shiro.test;

import com.hf.game.shiro.config.ShiroConfig;
import com.hf.game.shiro.config.ShiroConfig1;
import com.hf.game.shiro.config.configparems.ShiroCasParems;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 123 on 2019-6-8.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestConfig {
    @Value("${shiro.server.url.prefix}")
    String config;
    @Autowired
    ShiroCasParems parems;

    @Test
    public void t1() {
        System.out.println(ShiroCasParems.casFilterUrlPattern);
    }
}
