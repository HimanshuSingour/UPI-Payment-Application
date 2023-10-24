package com.payment.v2.Payment.Application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInformation {

    private String accountId;
    private String accountHolderName;
    private String contactEmail;
    private String contactPhone;
    private String gender;
    private String contactAddress;
    private String stateOfOrigin;
    private String pinCodeNumber;
    private String currentLocation;
    private String designation;
    private String country;
    private String accountNumber;
    private String password;
    private String ifscCode;
    private String bankName;
    private String bankBranch;
    private String routingNumber;
    private String bankPinCode;
    private String accountType;
    private String isHaveUpiId;
    private double accountBalance;
    private String status;
    private LocalDateTime localDateTime;
    private LocalDate accountOpenDate;
}
