package org.strategy_design_pattern_notification.context;

import org.strategy_design_pattern_notification.constants.enums.NotificationTypes;
import org.strategy_design_pattern_notification.models.NotificationRequest;
import org.strategy_design_pattern_notification.registry.NotificationRegistry;
import org.strategy_design_pattern_notification.service.NotificationStrategy;

/**
 * NotificationContext acts as the client-facing class that delegates
 * notification sending behavior to the appropriate {@link NotificationStrategy}
 * based on the {@link NotificationTypes}.
 *
 * <p>This class uses the Strategy + Registry design pattern to dynamically
 * resolve and execute the correct strategy for a given notification type.</p>
 */
public class NotificationContext {
    private final NotificationRegistry notificationRegistry;

    public NotificationContext() {
        notificationRegistry = NotificationRegistry.instance();
    }

    /**
     * Sends a notification using the appropriate strategy based on the given type.
     *
     * @param notificationType the type of notification (EMAIL, SMS, PUSH, etc.)
     * @param payload          the notification request payload (must extend {@link NotificationRequest})
     * @param <T>              the specific type of the payload
     */
    public <T extends NotificationRequest> void sendNotification(NotificationTypes notificationType, T payload) {
        notificationRegistry.getStrategy(notificationType).sendNotification(payload);
    }
}
