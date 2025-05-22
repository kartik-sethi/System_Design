package org.strategy_design_pattern_notification.serviceImpl;

import org.strategy_design_pattern_notification.models.PushNotificationRequest;
import org.strategy_design_pattern_notification.service.NotificationStrategy;

public class PushNotificationStrategyImpl implements NotificationStrategy<PushNotificationRequest> {

    @Override
    public void sendNotification(PushNotificationRequest request) {
        System.out.println("Successfully sent push notification");
    }
}
