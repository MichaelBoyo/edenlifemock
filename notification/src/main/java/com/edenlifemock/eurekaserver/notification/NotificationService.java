package com.edenlifemock.eurekaserver.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(String customerName){
        Notification notification = Notification.builder()
                .message("welcome to edenlife "+ customerName)
                .build();
      notificationRepository.saveAndFlush(notification);

      return new NotificationResponse(notification.getMessage());
    }


}
