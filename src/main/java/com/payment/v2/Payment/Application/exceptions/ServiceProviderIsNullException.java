package com.payment.v2.Payment.Application.exceptions;

public class ServiceProviderIsNullException extends RuntimeException{

    public ServiceProviderIsNullException(String message){
        super(message);
    }
}
