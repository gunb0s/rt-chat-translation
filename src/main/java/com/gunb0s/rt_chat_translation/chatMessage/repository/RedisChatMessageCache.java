package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessages;
import com.gunb0s.rt_chat_translation.common.redis.model.ChatRoomMessagesCopy;
import com.gunb0s.rt_chat_translation.common.redis.repository.ChatRoomMessagesCopyRepository;
import com.gunb0s.rt_chat_translation.common.redis.repository.ChatRoomMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.Queue;

@Repository
@RequiredArgsConstructor
public class RedisChatMessageCache {
    private final ChatRoomMessagesRepository chatRoomMessagesRepository;
    private final ChatRoomMessagesCopyRepository chatRoomMessagesCopyRepository;

    @Value("${spring.data.redis.expire}")
    private int expire;

    public void put(String roomId, Queue<ChatMessage> chatMessages) {
        LinkedList<ChatMessage> chatMessageList = new LinkedList<>(chatMessages);
        ChatRoomMessages chatRoomMessages = ChatRoomMessages.builder()
                .id(roomId)
                .chatMessages(chatMessageList)
                .build();
        ChatRoomMessagesCopy chatRoomMessagesCopy = chatRoomMessages.copy();

        chatRoomMessagesRepository.save(chatRoomMessages);
        chatRoomMessagesCopyRepository.save(chatRoomMessagesCopy);
    }

    public boolean containsKey(String roomId) {
        return Boolean.TRUE.equals(chatRoomMessagesRepository.existsById(roomId));
    }

    public LinkedList<ChatMessage> get(String roomId) {
        return chatRoomMessagesRepository.findById(roomId)
                .map(ChatRoomMessages::getChatMessages)
                .orElse(new LinkedList<>());
    }

    public void delete(String roomId) {
        chatRoomMessagesRepository.deleteById(roomId);
    }

    public void deleteAll() {
        chatRoomMessagesRepository.deleteAll();
    }
}
