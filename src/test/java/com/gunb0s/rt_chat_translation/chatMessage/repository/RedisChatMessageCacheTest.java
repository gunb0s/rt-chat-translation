package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.chatRoom.entity.ChatRoom;
import com.gunb0s.rt_chat_translation.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisChatMessageCacheTest {
    @Autowired
    private RedisChatMessageCache redisChatMessageCache;

    @Test
    void put() {
        // create chatMessage ArrayList
        User user = User.builder().build();
        ChatRoom chatRoom = ChatRoom.builder().build();
        ChatMessage chatMessage = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .payload("test")
                .build();

        // put chatMessage ArrayList
        LinkedList<ChatMessage> chatMessages = new LinkedList<>(List.of(chatMessage));
        redisChatMessageCache.put("roomId", chatMessages);

        // get chatMessage ArrayList
        List<ChatMessage> chatMessagesFromRedis = redisChatMessageCache.get("roomId");
        ChatMessage first = chatMessagesFromRedis.getFirst();
        ChatMessage first1 = chatMessages.getFirst();

        assertThat(first.getPayload()).isEqualTo(first1.getPayload());

        // delete chatMessage ArrayList
        redisChatMessageCache.delete("roomId");
    }
}
