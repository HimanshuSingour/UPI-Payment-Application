package com.payment.v2.Payment.Application.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private String providerId;
    private String planeId;
    private String planName;
    private double planAmount;
    private int validityDays;
    private int dataLimitMB;
    private int voiceMinutes;
    private String planDescription;
    private String providerName;
    private boolean isInternational;
    private boolean isLimitedTimeOffer;
    private String planType;
    private String dataUsagePolicy;
    private String activationCode;
    private String coverageArea;
    private String specialNotes;
    private String additionalBenefits;
}
