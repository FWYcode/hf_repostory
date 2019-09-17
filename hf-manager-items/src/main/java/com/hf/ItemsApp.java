package com.hf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 123 on 2019-6-19.
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class ItemsApp {
    public static void main(String[] args) {
        SpringApplication.run(ItemsApp.class);
    }
}
