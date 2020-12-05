package com.entian.common.httpclient;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author jianggangli
 * @version 1.0 2020/7/1 11:29
 * 功能:
 */
@Configuration
public class BaseBeanConfigure {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new FeignClientInterceptor()));
        return restTemplate;
    }
}
