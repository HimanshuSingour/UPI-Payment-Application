package com.payment.v2.Payment.Application.controller;

import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeRequest;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeResponse;
import com.payment.v2.Payment.Application.dto.ProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.service.TelecomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/telecom/v10/pro")
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
    ResponseEntity<RechargePlanes> addRechargesPacks(@RequestBody ServiceProviderRequest serviceProviderRequest){
        RechargePlanes Re = telecomService.addRechargePlan(serviceProviderRequest);
        return new ResponseEntity<RechargePlanes>(Re , HttpStatus.ACCEPTED);
    }

    // users
    @GetMapping("/get/v8/plans/{providedId}")
    ResponseEntity<List<RechargePlanes>> getRechargeById(@PathVariable String providedId){
        List<RechargePlanes> t = telecomService.getAllRechargePlansById(providedId);
        return new ResponseEntity<>(t, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/v8/plans/recharge/now")
    ResponseEntity<RechargeResponse> rechargeNow(@RequestBody RechargeRequest rechargeRequest){
       RechargeResponse response = telecomService.rechargeNow(rechargeRequest);
        return new ResponseEntity<RechargeResponse>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/packs/below/amount")
    ResponseEntity<List<RechargePlanes>> giveByBelowAmount(@RequestBody ProviderRequest providerRequest){
        List<RechargePlanes> response = telecomService.getAllRechargeAboveTheGivenAmount(providerRequest);
        return new ResponseEntity<List<RechargePlanes>>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/packs/above/amount")
    ResponseEntity<List<RechargePlanes>> giveByAboveAmount(@RequestBody ProviderRequest providerRequest){
        List<RechargePlanes> response = telecomService.getAllRechargeAboveTheGivenAmount(providerRequest);
        return new ResponseEntity<List<RechargePlanes>>(response, HttpStatus.ACCEPTED);
    }

}
