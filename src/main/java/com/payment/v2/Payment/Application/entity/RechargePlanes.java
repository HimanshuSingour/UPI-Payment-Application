package com.payment.v2.Payment.Application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RECHARGE_PLANE")
public class RechargePlanes {


    @Id
    private String planeId;
    private String providerId;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_Recharges")
    private ServiceProvider serviceProvider;


}
