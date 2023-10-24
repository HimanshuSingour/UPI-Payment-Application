package com.payment.v2.Payment.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Configurations {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
