package com.gunb0s.rt_chat_translation.common.redis.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessages;
import com.gunb0s.rt_chat_translation.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.LinkedList;

@SpringBootTest
class ChatRoomMessagesRepositoryTest {
    @Autowired
    private ChatRoomMessagesRepository chatRoomMessagesRepository;

    @Test
    void test() {
        User user = User.builder()
                .username("test")
                .build();
        ChatMessage chatMessage = ChatMessage.builder()
                .payload("test")
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        LinkedList<ChatMessage> chatMessages = new LinkedList<>();
        chatMessages.add(chatMessage);
        chatMessages.add(chatMessage);
        chatMessages.add(chatMessage);
        chatMessages.add(chatMessage);


        ChatRoomMessages chatRoomMessages = new ChatRoomMessages();
        chatRoomMessages.setId("test");
        chatRoomMessages.setChatMessages(chatMessages);

        chatRoomMessagesRepository.save(chatRoomMessages);

        ChatRoomMessages saved = chatRoomMessagesRepository.findById("test").get();
        LinkedList<ChatMessage> chatMessages1 = saved.getChatMessages();
        chatMessages1.forEach(s -> System.out.println(s.getCreatedDate()));
    }
}
