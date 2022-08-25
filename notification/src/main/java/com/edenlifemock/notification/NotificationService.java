package com.edenlifemock.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationResponse sendNotification(Long customerId){
        Notification notification = Notification.builder()
                .message("weclome to edenlife")
                .build();
      notificationRepository.saveAndFlush(notification);

      return new NotificationResponse(notification.getMessage());
    }


}
