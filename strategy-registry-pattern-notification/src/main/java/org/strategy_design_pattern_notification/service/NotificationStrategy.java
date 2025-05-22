package org.strategy_design_pattern_notification.service;

/**
 * NotificationStrategy defines the contract for sending notifications
 * using a specific strategy implementation.
 *
 * <p>This interface follows the Strategy Design Pattern, allowing different
 * notification mechanisms (e.g., Email, SMS, Push) to be implemented and
 * selected at runtime.</p>
 *
 * @param <T> the type of the notification request payload
 */
public interface NotificationStrategy<T> {

    void sendNotification(T request);
}
