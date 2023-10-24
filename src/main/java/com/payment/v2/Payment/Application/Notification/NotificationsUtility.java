package com.payment.v2.Payment.Application.Notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class NotificationsUtility {

    NotificationConfig notificationConfig = new NotificationConfig();

    public void sendForRechargeDoneAndAccountBalanceDone(double re) {
        String messageBody = "Your recharge has been successfully done, " + re + " balance left in your bank account";
        notificationConfig.sendSMS(messageBody);
    }




}
