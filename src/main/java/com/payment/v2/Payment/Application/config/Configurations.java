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

    public static final String URL_FOR_ACCOUNT_SERVICE = "http://localhost:9090/finance/v1/bank/v4/bharat/get-account-details/";
    public static final String URL_FOR_ACCOUNT_UPDATE_SERVICE = "http://localhost:9090/finance/v1/bank/v4/bharat/update/money";
}
