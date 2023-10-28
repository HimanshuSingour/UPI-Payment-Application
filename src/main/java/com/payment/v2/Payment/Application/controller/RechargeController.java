package com.payment.v2.Payment.Application.controller;

import com.payment.v2.Payment.Application.dto.ActivationInfo;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeRequest;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeResponse;
import com.payment.v2.Payment.Application.dto.ProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.service.TelecomService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telecom/v10/pro")
@Slf4j
public class RechargeController {

    @Autowired
    private TelecomService telecomService;

    // for Developers
    @PostMapping("/save/v7/provider")
    ResponseEntity<ServiceProvider> addProvider(@RequestBody ServiceProvider netBankingRequest) {
        log.info("Received request to add a provider: {}", netBankingRequest);
        ServiceProvider response = telecomService.addServiceProvides(netBankingRequest);
        log.info("Provider added: {}", response);
        return new ResponseEntity<ServiceProvider>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/add/v8/plans")
    ResponseEntity<RechargePlanes> addRechargesPacks(@RequestBody ServiceProviderRequest serviceProviderRequest) {
        log.info("Received request to add recharge plans: {}", serviceProviderRequest);
        RechargePlanes Re = telecomService.addRechargePlan(serviceProviderRequest);
        log.info("Recharge plans added: {}", Re);
        return new ResponseEntity<RechargePlanes>(Re, HttpStatus.ACCEPTED);
    }

    // users
    @GetMapping("/get/v8/plans/{providedId}")
    ResponseEntity<List<RechargePlanes>> getRechargeById(@PathVariable String providedId) {
        log.info("Received request to get recharge plans for provider ID: {}", providedId);
        List<RechargePlanes> t = telecomService.getAllRechargePlansById(providedId);
        log.info("Recharge plans retrieved: {}", t);
        return new ResponseEntity<>(t, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/v8/plans/recharge/now")
    @Retry(name = "AccountInformationService", fallbackMethod = "AccountInformationServiceFallBack")
    ResponseEntity<RechargeResponse> rechargeNow(@RequestBody RechargeRequest rechargeRequest) {
        log.info("Received request to recharge now: {}", rechargeRequest);
        RechargeResponse response = telecomService.rechargeNow(rechargeRequest);
        log.info("Recharge completed: {}", response);
        return new ResponseEntity<RechargeResponse>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/packs/above/amount")
    ResponseEntity<List<RechargePlanes>> giveByAboveAmount(@RequestBody ProviderRequest providerRequest) {
        log.info("Received request to get recharge plans above a certain amount: {}", providerRequest);
        List<RechargePlanes> response = telecomService.getAllRechargeAboveTheGivenAmount(providerRequest);
        log.info("Recharge plans above the given amount retrieved: {}", response);
        return new ResponseEntity<List<RechargePlanes>>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/packs/below/amount")
    ResponseEntity<List<RechargePlanes>> giveByBelowAmount(@RequestBody ProviderRequest providerRequest) {
        log.info("Received request to get recharge plans below a certain amount: {}", providerRequest);
        List<RechargePlanes> response = telecomService.getAllRechargeAboveTheGivenAmount(providerRequest);
        log.info("Recharge plans below the given amount retrieved: {}", response);
        return new ResponseEntity<List<RechargePlanes>>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/packs/activating/info/{planId}")
    ResponseEntity<ActivationInfo> getActivationInfo(@PathVariable String planId) {
        log.info("Received request to get activation information for plan ID: {}", planId);
        ActivationInfo response = telecomService.getActivationInfoByRechargePacks(planId);
        log.info("Activation information retrieved: {}", response);
        return new ResponseEntity<ActivationInfo>(response, HttpStatus.ACCEPTED);
    }
}
