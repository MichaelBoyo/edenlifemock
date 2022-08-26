package com.edenlifemock.clients.notification;

public record NotificationRequest(
        Long customerId,
        String customerName,
        String message,
        String email
) {
}
