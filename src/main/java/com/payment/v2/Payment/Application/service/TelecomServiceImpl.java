package com.payment.v2.Payment.Application.service;

import com.payment.v2.Payment.Application.dto.RechargePlanActivitionInfoRequest;
import com.payment.v2.Payment.Application.dto.ServiceProviderRequest;
import com.payment.v2.Payment.Application.entity.RechargePlanes;
import com.payment.v2.Payment.Application.entity.Request;
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
    public ServiceProvider addServiceProvides(ServiceProvider service) {

        if (service == null) {
            throw new ServiceProviderIsNullException("Service provider is null");
        }

        if (service.getServiceProviderName() == null || service.getServiceProviderName().isEmpty()) {
            throw new ServiceProviderValidationException("Service provider name is required");
        }

        ServiceProvider provider = ServiceProvider.builder()
                .providerId(service.getProviderId())

                .serviceProviderName(service.getServiceProviderName())
                .website(service.getWebsite())
                .build();

        serviceProviderRepositories.save(provider);

        return provider;
    }

    public String addRechargePlan(Request request) {

        Optional<ServiceProvider> serviceProvider = serviceProviderRepositories.findById(request.getProviderId());
        Optional<RechargePlanes> rechargePlanes = rechangeRepositories.findById(request.getPlaneId());

        RechargePlanes recharge = RechargePlanes.builder()
                .planeId(request.getPlaneId())
                .activationCode(request.getActivationCode())
                .planAmount(request.getPlanAmount())
                .planDescription(request.getPlanDescription())
                .additionalBenefits(request.getAdditionalBenefits())
                .planName(request.getPlanName())
                .planType(request.getPlanType())
                .dataLimitMB(request.getDataLimitMB())
                .providerName(request.getProviderName())
                .dataUsagePolicy(request.getDataUsagePolicy())
                .specialNotes(request.getSpecialNotes())
                .validityDays(request.getValidityDays())
                .voiceMinutes(request.getVoiceMinutes())
                .isInternational(request.isInternational())
                .coverageArea(request.getCoverageArea())
                .isLimitedTimeOffer(request.isLimitedTimeOffer())
                .serviceProvider(serviceProvider.get())
                .providerId(request.getProviderId())
                .build();

        rechangeRepositories.save(recharge);

        return "saved";

    }


    @Override
    public List<RechargePlanes> getAllRechargePlansById(String providedId) {
        return rechangeRepositories.findByProviderId(providedId);
    }

    @Override
    public List<RechargePlanes> getAllRechargeAboveTheGivenAmount(ServiceProviderRequest serviceProviderRequest, double aboveAmount) {
        return null;
    }

    @Override
    public List<RechargePlanes> getAllRechargeBelowTheGivenAmount(ServiceProviderRequest serviceProviderRequest, double BelowAmount) {
        return null;
    }

    @Override
    public RechargePlanActivitionInfoRequest getActivationInfo(ServiceProviderRequest serviceProviderRequest, String packId) {
        return null;
    }

}


