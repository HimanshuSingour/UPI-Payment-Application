package com.payment.v2.Payment.Application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ExternalServices {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    public static final String URL_FOR_ACCOUNT_SERVICE = "http://localhost:7777/finance/v1/bank/v4/bharat/get-account-details/";
    public static final String URL_FOR_ACCOUNT_UPDATE_SERVICE = "http://localhost:7777/finance/v1/bank/v4/bharat/update/money";
}
