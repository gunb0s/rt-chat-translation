package com.gunb0s.rt_chat_translation.messageBroker;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

class CreateTopicTest {
    KafkaAdmin kafkaAdmin;

    public CreateTopicTest() {
        Map<String, Object> configs = Map.of(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"
        );
        kafkaAdmin = new KafkaAdmin(configs);
    }

    @Test
    @DisplayName("수동으로 카프카 토픽 생성")
    void manuallyCreateKafkaTopic() {
        NewTopic testTopic = TopicBuilder.name("test-topic")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();

        kafkaAdmin.createOrModifyTopics(testTopic);
    }
}
