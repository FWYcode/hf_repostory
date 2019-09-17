package com.hf.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 123 on 2019-7-3.
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class SearchApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchApp.class);
    }
    @Bean
    @LoadBalanced
    public RestTemplate getHttpTemplate(RestTemplateBuilder builder) {
        return new RestTemplate();
    }
}