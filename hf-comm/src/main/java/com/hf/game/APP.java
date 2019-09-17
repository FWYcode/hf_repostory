package com.hf.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by 123 on 2019-5-23.
 */
@SpringBootApplication
@ServletComponentScan
public class APP {
    public static void main(String[] args) {
        SpringApplication.run(APP.class);
    }
}
