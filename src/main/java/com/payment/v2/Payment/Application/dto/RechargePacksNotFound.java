package com.payment.v2.Payment.Application.dto;

public class RechargePacksNotFound extends RuntimeException{

    public RechargePacksNotFound(String message){
        super(message);
    }
}
