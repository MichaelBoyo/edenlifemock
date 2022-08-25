package com.edenlifemock.notification;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> sendNotification(@PathVariable Long id){
        log.info("Sending notification to {}",id);
        return new ResponseEntity<>(notificationService.sendNotification(id), HttpStatus.OK);
    }


}
