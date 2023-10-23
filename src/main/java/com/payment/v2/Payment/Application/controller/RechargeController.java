package com.payment.v2.Payment.Application.controller;

import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.Request;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.service.TelecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("telecom/v10/pro")
@RestController
public class RechargeController {

    @Autowired
    private TelecomService telecomService;


    // for Developers
    @PostMapping("/save/v7/provider")
    ResponseEntity<ServiceProvider> addProvider(@RequestBody ServiceProvider netBankingRequest){
        ServiceProvider response = telecomService.addServiceProvides(netBankingRequest);
        return new ResponseEntity<ServiceProvider>(response , HttpStatus.ACCEPTED);
    }

    @GetMapping("/add/v8/plans")
    ResponseEntity<String> addRechargesPacks(@RequestBody Request request){
         String string = telecomService.addRechargePlan(request);
        return new ResponseEntity<String>(string , HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/v8/plans/{providedId}")
    ResponseEntity<List<RechargePlanes>> getAllRecharge(@PathVariable String providedId){
        List<RechargePlanes> t = telecomService.getAllRechargePlansById(providedId);
        return new ResponseEntity<>(t, HttpStatus.ACCEPTED);
    }

}
