package com.hf.game.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 123 on 2019-7-6.
 */
@Configuration
public class ElasticSearchConfig {
    @Value("${hf.search.host.list}")
    String addr;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(this.pareAddress(addr)));
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder(this.pareAddress(addr)).build();
    }

    private HttpHost[] pareAddress(String addr) {
        String[] addrs = addr.trim().split(",");
        HttpHost[] hosts = new HttpHost[addrs.length];
        for (int i = 0; i < addrs.length; i++) {
            hosts[i] = new HttpHost(addrs[i].trim().split(":")[0],
                    Integer.parseInt(addrs[i].trim().split(":")[1]),
                    "http");
        }

        return hosts;
    }
}
