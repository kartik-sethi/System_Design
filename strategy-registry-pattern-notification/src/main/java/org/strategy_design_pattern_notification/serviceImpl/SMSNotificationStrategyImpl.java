package org.strategy_design_pattern_notification.serviceImpl;

import org.strategy_design_pattern_notification.models.SMSNotificationRequest;
import org.strategy_design_pattern_notification.service.NotificationStrategy;

public class SMSNotificationStrategyImpl implements NotificationStrategy<SMSNotificationRequest> {

    @Override
    public void sendNotification(SMSNotificationRequest request) {
        System.out.println("Successfully sent sms notification");
    }
}
