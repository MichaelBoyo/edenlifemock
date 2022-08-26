package com.edenlifemock.notification;

import com.edenlifemock.clients.notification.NotificationRequest;
import com.edenlifemock.clients.notification.NotificationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(NotificationRequest notificationRequest){
        Notification notification = Notification.builder()
                .message(notificationRequest.message())
                .toCustomerId(notificationRequest.customerId())
                .toCustomerEmail(notificationRequest.email())
                .sender("EdenLife")
                .build();
      notificationRepository.saveAndFlush(notification);

      return new NotificationResponse(notification.getMessage());
    }
}