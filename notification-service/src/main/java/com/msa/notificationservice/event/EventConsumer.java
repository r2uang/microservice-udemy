package com.msa.notificationservice.event;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {
    @RetryableTopic(attempts = "4"
            , backoff = @Backoff(delay = 1000, multiplier = 2)
            , autoCreateTopics = "true"
            , dltStrategy = DltStrategy.FAIL_ON_ERROR
            , include = {RetriableException.class, RuntimeException.class})
    @KafkaListener(topics = "topic_53", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
                log.info("Received group foo message: " + message);
    }

    @DltHandler
    void handleDltMessage(@Payload String message){
        log.info("DLT receive message: " + message);
    }

}
