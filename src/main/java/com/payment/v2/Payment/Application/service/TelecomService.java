package com.payment.v2.Payment.Application.service;
import com.payment.v2.Payment.Application.dto.RechargePlanActivitionInfoRequest;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.Request;
import com.payment.v2.Payment.Application.entity.ServiceProvider;


import java.util.List;

public interface TelecomService {

    // for Developers
    ServiceProvider addServiceProvides(ServiceProvider serviceProvider);
    String addRechargePlan(Request request);

    // Users
    List<RechargePlanes> getAllRechargePlansById(String providedId);
    List<RechargePlanes> getAllRechargeAboveTheGivenAmount(ServiceProviderRequest serviceProviderRequest , double aboveAmount);
    List<RechargePlanes> getAllRechargeBelowTheGivenAmount(ServiceProviderRequest serviceProviderRequest , double BelowAmount);
    RechargePlanActivitionInfoRequest getActivationInfo(ServiceProviderRequest serviceProviderRequest , String packId);








}
