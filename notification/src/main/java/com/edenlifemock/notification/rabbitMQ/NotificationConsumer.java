package com.edenlifemock.notification.rabbitMQ;

import com.edenlifemock.clients.notification.NotificationRequest;
import com.edenlifemock.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest){
        log.info("consumed {} from queue",notificationRequest);
        notificationService.sendNotification(notificationRequest);
    }
}
