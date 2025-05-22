package org.strategy_design_pattern_notification.serviceImpl;

import org.strategy_design_pattern_notification.models.EmailNotificationRequest;
import org.strategy_design_pattern_notification.service.NotificationStrategy;

public class EmailNotificationStrategyImpl implements NotificationStrategy<EmailNotificationRequest> {

    @Override
    public void sendNotification(EmailNotificationRequest request) {
        System.out.println("Successfully sent email notification");
    }
}
