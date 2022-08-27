package com.edenlifemock.notification;

import com.edenlifemock.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/notify")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("Sending notification to {}",notificationRequest);
        return new ResponseEntity<>(notificationService.sendNotification(notificationRequest), HttpStatus.OK);
    }
}
