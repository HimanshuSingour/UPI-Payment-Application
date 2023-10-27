package com.payment.v2.Payment.Application.exceptions;

public class ServiceIsDownException extends RuntimeException{

    public ServiceIsDownException(String message){
        super(message);
    }
}
