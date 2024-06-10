package com.gunb0s.rt_chat_translation.messageBroker.kafka;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.messageBroker.MessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaBroker implements MessageBroker {
    private final KafkaAdmin kafkaAdmin;

    @Override
    public void createChatRoom(String subscriptionUrl) {
        NewTopic testTopic = TopicBuilder.name(subscriptionUrl)
                .partitions(1)
                .replicas(1)
                .build();

        kafkaAdmin.createOrModifyTopics(testTopic);

        log.info("Created topic: {}", subscriptionUrl);
    }

    @Override
    public void produceMessage(String subscriptionUrl, ChatMessage chatMessage) {

    }
}
