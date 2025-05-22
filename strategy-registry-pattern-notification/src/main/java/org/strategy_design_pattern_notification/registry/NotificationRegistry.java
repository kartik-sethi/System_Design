package org.strategy_design_pattern_notification.registry;

import static org.strategy_design_pattern_notification.constants.enums.NotificationTypes.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import org.strategy_design_pattern_notification.constants.enums.NotificationTypes;
import org.strategy_design_pattern_notification.models.NotificationRequest;
import org.strategy_design_pattern_notification.service.NotificationStrategy;
import org.strategy_design_pattern_notification.serviceImpl.EmailNotificationStrategyImpl;
import org.strategy_design_pattern_notification.serviceImpl.PushNotificationStrategyImpl;
import org.strategy_design_pattern_notification.serviceImpl.SMSNotificationStrategyImpl;

/**
 * NotificationRegistry is a singleton-based registry that maps each {@link NotificationTypes}
 * to its corresponding {@link NotificationStrategy} implementation.
 *
 * <p>This class combines the Registry Pattern with the Strategy Pattern, enabling dynamic
 * resolution of strategies at runtime for sending notifications (e.g., Email, SMS, Push).</p>
 */
public final class NotificationRegistry {
    private static volatile NotificationRegistry INSTANCE;
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * Internal map to store the relationship between {@link NotificationTypes}
     * and their corresponding {@link NotificationStrategy} implementations.
     */
    private static final Map<NotificationTypes, NotificationStrategy<? extends NotificationRequest>> notificationRegistryMap = new HashMap<>();

    private NotificationRegistry() {
    }

    /**
     * Returns the singleton instance of NotificationRegistry.
     *
     * @return the singleton NotificationRegistry instance
     */
    public static NotificationRegistry instance() {
        if (Objects.isNull(INSTANCE)) {
            lock.lock();
            if (Objects.isNull(INSTANCE)) {
                INSTANCE = new NotificationRegistry();
                notificationRegistryMap.put(EMAIL, new EmailNotificationStrategyImpl());
                notificationRegistryMap.put(SMS, new SMSNotificationStrategyImpl());
                notificationRegistryMap.put(PUSH, new PushNotificationStrategyImpl());
            }
            lock.unlock();
        }
        return INSTANCE;
    }

    /**
     * Retrieves the appropriate {@link NotificationStrategy} based on the given notification type.
     *
     * @param notificationType the type of notification (EMAIL, SMS, PUSH, etc.)
     * @param <T>              the type of request that extends {@link NotificationRequest}
     * @return the strategy implementation associated with the given type
     * @throws IllegalArgumentException if no strategy is registered for the provided type
     */
    @SuppressWarnings("unchecked")
    public <T extends NotificationRequest> NotificationStrategy<T> getStrategy(NotificationTypes notificationType) {
        if (notificationRegistryMap.containsKey(notificationType)) {
            return (NotificationStrategy<T>) notificationRegistryMap.get(notificationType);
        } else {
            throw new IllegalArgumentException(
                    String.format("Notification Type: %s not supported by notification registry", notificationType));
        }
    }
}
