package com.payment.v2.Payment.Application.service;

import com.payment.v2.Payment.Application.dto.ProviderRequest;
import com.payment.v2.Payment.Application.dto.ActivationRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.ServiceProvider;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderIsNullException;
import com.payment.v2.Payment.Application.exceptions.ServiceProviderValidationException;
import com.payment.v2.Payment.Application.repository.RechangeRepositories;
import com.payment.v2.Payment.Application.repository.ServiceProviderRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelecomServiceImpl implements TelecomService {

    @Autowired
    private ServiceProviderRepositories serviceProviderRepositories;

    @Autowired
    private RechangeRepositories rechangeRepositories;


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
        if(serviceProvider.isPresent()){

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

        }
        else{

            throw new ServiceProviderValidationException("Service Provider Detail is not Found..");
        }

        return recharge;
    }

    // From here users will user belows


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


