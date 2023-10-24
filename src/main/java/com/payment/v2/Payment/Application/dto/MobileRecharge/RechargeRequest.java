package com.payment.v2.Payment.Application.dto.MobileRecharge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRequest {

    private String serviceProviderName;
    private String planId;
    private String planName;
    private String planAmount;
}
