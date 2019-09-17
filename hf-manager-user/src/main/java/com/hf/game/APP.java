package com.hf.game;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by 123 on 2019-6-3.
 */
@SpringBootApplication
//@MapperScan("com.hf.game.mapper")
public class APP {
    public static void main(String[] args) {
        SpringApplication.run(APP.class);
    }
}
