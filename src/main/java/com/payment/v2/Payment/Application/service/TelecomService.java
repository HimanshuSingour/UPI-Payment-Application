package com.payment.v2.Payment.Application.service;

import com.payment.v2.Payment.Application.dto.ActivationInfo;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeRequest;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeResponse;
import com.payment.v2.Payment.Application.dto.ProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.entity.TransactionHistory;


import java.util.List;

public interface TelecomService {

    // for Developers
    ServiceProvider addServiceProvides(ServiceProvider serviceProvider);

    RechargePlanes addRechargePlan(ServiceProviderRequest serviceProviderRequest);

    //Users
    List<RechargePlanes> getAllRechargePlansById(String providedId);

    List<RechargePlanes> getAllRechargeAboveTheGivenAmount(ProviderRequest providerRequest);

    List<RechargePlanes> getAllRechargeBelowTheGivenAmount(ProviderRequest providerRequest, double BelowAmount);

    ActivationInfo getActivationInfoByRechargePacks(String planId);

    RechargeResponse rechargeNow(RechargeRequest rechargeRequest);

    //ToDo : Need To Implement
//    CancellationsRequest cancelRequest(CancellationsResponse cancellations);
//
//    TransactionHistory getAllRechargesTransactionHistory(TransactionResponse transactionResponse);
//
//    RechargeStatements getRechargeStatements()


}
