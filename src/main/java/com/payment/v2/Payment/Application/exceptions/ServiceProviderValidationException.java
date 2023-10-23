package com.payment.v2.Payment.Application.exceptions;

public class ServiceProviderValidationException extends RuntimeException{

    public ServiceProviderValidationException(String message){
        super(message);
    }
}
