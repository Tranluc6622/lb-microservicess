package com.elcom.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProperties {
    @Value("${notify-trigger.worker.queue}")
    public static String NOTIFY_TRIGGER_QUEUE;

    @Value("${notify-trigger.worker.queue}")
}
