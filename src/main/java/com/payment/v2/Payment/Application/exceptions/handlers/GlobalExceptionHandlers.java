package com.payment.v2.Payment.Application.exceptions.handlers;

import com.payment.v2.Payment.Application.exceptions.RechargePlanNotFoundException;
import com.payment.v2.Payment.Application.exceptions.ServiceIsDownException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderIsNullException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(ServiceProviderIsNullException.class)
    ResponseEntity<ErrorMessages> serviceProviderNullException(ServiceProviderIsNullException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setErrors(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }

    @ExceptionHandler(ServiceProviderValidationException.class)
    ResponseEntity<ErrorMessages> validationExceptions(ServiceProviderValidationException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setErrors(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }

    @ExceptionHandler(RechargePlanNotFoundException.class)
    ResponseEntity<ErrorMessages> rechargePlanNotFound(RechargePlanNotFoundException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setErrors(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }

    @ExceptionHandler(ServiceIsDownException.class)
    ResponseEntity<ErrorMessages> serverIsDown(ServiceIsDownException ex){
        ErrorMessages errorMessages = new ErrorMessages();
        errorMessages.setErrors(ex.getMessage());
        return new ResponseEntity<>(errorMessages , HttpStatus.OK);
    }
}
