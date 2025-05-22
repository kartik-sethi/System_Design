package org.strategy_design_pattern_notification;

import org.strategy_design_pattern_notification.constants.enums.NotificationTypes;
import org.strategy_design_pattern_notification.context.NotificationContext;
import org.strategy_design_pattern_notification.models.EmailNotificationRequest;
import org.strategy_design_pattern_notification.models.PushNotificationRequest;
import org.strategy_design_pattern_notification.models.SMSNotificationRequest;

public class Main {
    public static void main(String[] args) {
        NotificationContext context = new NotificationContext();
        context.sendNotification(NotificationTypes.EMAIL, new EmailNotificationRequest());
        context.sendNotification(NotificationTypes.SMS, new SMSNotificationRequest());
        context.sendNotification(NotificationTypes.PUSH, new PushNotificationRequest());
    }
}
