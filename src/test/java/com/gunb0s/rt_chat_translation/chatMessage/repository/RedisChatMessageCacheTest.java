package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import com.gunb0s.rt_chat_translation.user.controller.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisChatMessageCacheTest {
    @Autowired
    private RedisChatMessageCache redisChatMessageCache;

    @Test
    void put() {
        // create chatMessage ArrayList
        UserDto userDto = UserDto.builder()
                .id("userId")
                .username("userName")
                .build();
        ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                .sender(userDto)
                .payload("payload")
                .build();

        // put chatMessage ArrayList
        ArrayList<ChatMessageDto> chatMessages = new ArrayList<>(List.of(chatMessageDto));
        redisChatMessageCache.put("roomId", chatMessages);

        // get chatMessage ArrayList
        List<ChatMessageDto> chatMessagesFromRedis = redisChatMessageCache.get("roomId");
        ChatMessageDto first = chatMessagesFromRedis.getFirst();
        ChatMessageDto first1 = chatMessages.getFirst();

        assertThat(first.getPayload()).isEqualTo(first1.getPayload());

        // delete chatMessage ArrayList
        redisChatMessageCache.delete("roomId");
    }
}
