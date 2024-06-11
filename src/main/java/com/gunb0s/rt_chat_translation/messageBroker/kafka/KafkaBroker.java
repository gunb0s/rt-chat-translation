package com.gunb0s.rt_chat_translation.messageBroker.kafka;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.messageBroker.MessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Slf4j
public class KafkaBroker implements MessageBroker {
    private final KafkaAdmin kafkaAdmin;
    private final KafkaTemplate<String, String> kafkaTemplate;

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
    public void produceMessage(ChatRoom chatRoom, ChatMessage chatMessage) {
        String subscriptionTopic = "rtChatMessage";
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(subscriptionTopic, chatMessage.getPayload());
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[{}] with offset=[{}]", chatMessage.getPayload(), result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message=[{}] due to : {}", chatMessage.getPayload(), ex.getMessage());
            }
        });
    }
}
