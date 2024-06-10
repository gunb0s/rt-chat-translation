package com.gunb0s.rt_chat_translation.messageBroker;

import com.gunb0s.rt_chat_translation.messageBroker.kafka.KafkaBroker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class MessageBrokerConfig {
    @Bean
    public MessageBroker kafkaBroker(KafkaAdmin kafkaAdmin) {
        return new KafkaBroker(kafkaAdmin);
    }
}
