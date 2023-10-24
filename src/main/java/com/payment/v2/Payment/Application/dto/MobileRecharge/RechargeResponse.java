package com.payment.v2.Payment.Application.dto.MobileRecharge;

import com.payment.v2.Payment.Application.entity.RechargePlanes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeResponse {

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
    private String messageStatus;

}
