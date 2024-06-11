package com.gunb0s.rt_chat_translation.messageBroker.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    @KafkaListener(
            topics = "rtChatMessage",
            groupId = "rt-chat-translation"
    )
    public void consume(String chatMessage) {
        log.info("Consumed message: {}", chatMessage);
    }
}
