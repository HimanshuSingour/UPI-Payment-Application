package com.payment.v2.Payment.Application.service;

import com.payment.v2.Payment.Application.entity.AccountInformation;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeRequest;
import com.payment.v2.Payment.Application.dto.MobileRecharge.RechargeResponse;
import com.payment.v2.Payment.Application.dto.ProviderRequest;
import com.payment.v2.Payment.Application.dto.ActivationRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.entity.UpdatingAccountinfomation;
import com.payment.v2.Payment.Application.exceptions.RechargePlanNotFoundException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderIsNullException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderValidationException;
import com.payment.v2.Payment.Application.repository.RechangeRepositories;
import com.payment.v2.Payment.Application.repository.ServiceProviderRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.payment.v2.Payment.Application.Utile.MobileRechargeConstant.*;

@Service
public class TelecomServiceImpl implements TelecomService {

    @Autowired
    private ServiceProviderRepositories serviceProviderRepositories;

    @Autowired
    private RechangeRepositories rechangeRepositories;

    @Autowired
    private RestTemplate restTemplate;


    // For Client (Add Provider Info)
    @Override
    public ServiceProvider addServiceProvides(ServiceProvider serviceProvider) {

        if (serviceProvider == null) {
            throw new ServiceProviderIsNullException("Service Provider Detail is not Found..");
        }

        if (serviceProvider.getServiceProviderName() == null || serviceProvider.getServiceProviderName().isEmpty()) {
            throw new ServiceProviderValidationException("Service provider name is required");
        }

        ServiceProvider provider = ServiceProvider.builder()
                .providerId(serviceProvider.getProviderId())
                .serviceProviderName(serviceProvider.getServiceProviderName())
                .website(serviceProvider.getWebsite())
                .build();

        serviceProviderRepositories.save(provider);

        return provider;
    }

    public RechargePlanes addRechargePlan(ServiceProviderRequest serviceProviderRequest) {

        RechargePlanes recharge = null;

        Optional<ServiceProvider> serviceProvider = serviceProviderRepositories.findById(serviceProviderRequest.getProviderId());
        if (serviceProvider.isPresent()) {

            Optional<RechargePlanes> rechargePlanes = rechangeRepositories.findById(serviceProviderRequest.getPlaneId());
            recharge = RechargePlanes.builder()
                    .planeId(serviceProviderRequest.getPlaneId())
                    .activationCode(serviceProviderRequest.getActivationCode())
                    .planAmount(serviceProviderRequest.getPlanAmount())
                    .planDescription(serviceProviderRequest.getPlanDescription())
                    .additionalBenefits(serviceProviderRequest.getAdditionalBenefits())
                    .planName(serviceProviderRequest.getPlanName())
                    .planType(serviceProviderRequest.getPlanType())
                    .dataLimitMB(serviceProviderRequest.getDataLimitMB())
                    .providerName(serviceProviderRequest.getProviderName())
                    .dataUsagePolicy(serviceProviderRequest.getDataUsagePolicy())
                    .specialNotes(serviceProviderRequest.getSpecialNotes())
                    .validityDays(serviceProviderRequest.getValidityDays())
                    .voiceMinutes(serviceProviderRequest.getVoiceMinutes())
                    .isInternational(serviceProviderRequest.isInternational())
                    .coverageArea(serviceProviderRequest.getCoverageArea())
                    .isLimitedTimeOffer(serviceProviderRequest.isLimitedTimeOffer())
                    .serviceProvider(serviceProvider.get())
                    .providerId(serviceProviderRequest.getProviderId())
                    .build();

            rechangeRepositories.save(recharge);

        } else {

            throw new ServiceProviderValidationException("Service Provider Detail is not Found..");
        }

        return recharge;
    }

    // From here users will user belows

    @Override
    public RechargeResponse rechargeNow(RechargeRequest rechargeRequest) {

        RechargeResponse rechargeResponse = new RechargeResponse();

        Optional<ServiceProvider> serviceProvider = serviceProviderRepositories.findByServiceProviderName(rechargeRequest.getServiceProviderName());
        if (serviceProvider.isPresent() && Objects.equals(serviceProvider.get().getServiceProviderName(), rechargeRequest.getPlanName())) {

            Optional<RechargePlanes> rechargePlanesId = rechangeRepositories
                    .findByPlanIdAndPlanNameAndPlanAmount(rechargeRequest.getPlanId(), rechargeRequest.getPlanName(),
                            String.valueOf(rechargeRequest.getPlanAmount()));

            if (rechargePlanesId.isPresent()) {

                // Calling bank Account Information Service
                ResponseEntity<AccountInformation> response = restTemplate.getForEntity(URL_FOR_ACCOUNT_SERVICE + rechargeRequest.getAccountNumber() + "/" + rechargeRequest.getIfscCode() + "/" + rechargeRequest.getPassword(), AccountInformation.class);
                AccountInformation accountInformation = response.getBody();

                double remainAmount = 0;
                if (accountInformation != null) {

                    double accountBalance = accountInformation.getAccountBalance();
                    double rechargePack = rechargeRequest.getPlanAmount();
                    remainAmount = accountBalance - rechargePack; // send this remainAmount to account information adn update there account balance by sending to

                    // need to update the balance to another service like remainAmount -> accountBalance
                    String updateUrl = URL_FOR_ACCOUNT_UPDATE_SERVICE +  rechargeRequest.getAccountNumber() + "/" + rechargeRequest.getPassword() + "/" + remainAmount;


                }

                RechargePlanes rechargePlanes = rechargePlanesId.get();
                rechargeResponse.setPlanName(rechargePlanes.getPlanName());
                rechargeResponse.setPlanAmount(rechargePlanes.getPlanAmount());
                rechargeResponse.setValidityDays(rechargePlanes.getValidityDays());
                rechargeResponse.setDataLimitMB(rechargePlanes.getDataLimitMB());
                rechargeResponse.setVoiceMinutes(rechargePlanes.getVoiceMinutes());
                rechargeResponse.setPlanDescription(rechargePlanes.getPlanDescription());
                rechargeResponse.setProviderName(rechargePlanes.getProviderName());
                rechargeResponse.setInternational(rechargePlanes.isInternational());
                rechargeResponse.setLimitedTimeOffer(rechargePlanes.isLimitedTimeOffer());
                rechargeResponse.setPlanType(rechargePlanes.getPlanType());
                rechargeResponse.setDataUsagePolicy(rechargePlanes.getDataUsagePolicy());
                rechargeResponse.setActivationCode(rechargePlanes.getActivationCode());
                rechargeResponse.setCoverageArea(rechargePlanes.getCoverageArea());
                rechargeResponse.setSpecialNotes(rechargePlanes.getSpecialNotes());
                rechargeResponse.setAdditionalBenefits(rechargePlanes.getAdditionalBenefits());
                rechargeResponse.setRechargeStatus(RECHARGE_SUCCESSFUL);
                rechargeResponse.setAccountStatus(DICUCTION_IN_ACCOUNT_BALANCE);
                rechargeResponse.setRemainAccountBalance(String.valueOf(remainAmount));

            } else {
                throw new RechargePlanNotFoundException("Apologies, the selected recharge pack details are not available in our database.");
            }

        } else {
            throw new RechargePlanNotFoundException("Apologies, the selected service provider details are not available in our database.");
        }

        return rechargeResponse;
    }


    @Override
    public List<RechargePlanes> getAllRechargePlansById(String providedId) {
        return rechangeRepositories.findByProviderId(providedId);
    }

    @Override
    public List<RechargePlanes> getAllRechargeAboveTheGivenAmount(ProviderRequest providerRequest, double aboveAmount) {
        return null;
    }

    @Override
    public List<RechargePlanes> getAllRechargeBelowTheGivenAmount(ProviderRequest providerRequest, double BelowAmount) {
        return null;
    }

    @Override
    public ActivationRequest getActivationInfo(ProviderRequest providerRequest, String packId) {
        return null;
    }


}



