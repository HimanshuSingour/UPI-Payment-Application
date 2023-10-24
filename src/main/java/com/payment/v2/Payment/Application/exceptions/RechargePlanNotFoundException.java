package com.payment.v2.Payment.Application.exceptions;

public class RechargePlanNotFoundException extends RuntimeException{

    public RechargePlanNotFoundException(String ex){
        super(ex);
    }
}
