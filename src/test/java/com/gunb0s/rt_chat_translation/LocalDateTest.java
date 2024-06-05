package com.gunb0s.rt_chat_translation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateTest {
    @Test
    public void testSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ChatMessage chatMessage = ChatMessage.builder()
                .createdAt(LocalDateTime.now())
                .build();

        String json = objectMapper.writeValueAsString(chatMessage);
        ChatMessage deserializedMessage = objectMapper.readValue(json, ChatMessage.class);

        assertThat(deserializedMessage.getCreatedDate()).isEqualTo(chatMessage.getCreatedDate());
    }
}
