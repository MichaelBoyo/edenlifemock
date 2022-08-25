package com.edenlifemock.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification sendNotification(Long customerId){
       return notificationRepository.saveAndFlush(Notification.builder()
               .message("weclome to edenlife")
               .build());
    }


}
