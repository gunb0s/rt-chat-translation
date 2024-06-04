package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.controller.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisChatMessageCache {
    private final RedisTemplate<String, ArrayList<ChatMessageDto>> redisTemplate;

    @Value("${spring.data.redis.expire}")
    private int expire;

    public void put(String roomId, ArrayList<ChatMessageDto> chatMessages) {
        redisTemplate.opsForValue().set(roomId, chatMessages);
        redisTemplate.expire(roomId, expire, TimeUnit.MINUTES);
    }

    public boolean containsKey(String roomId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(roomId));
    }

    public ArrayList<ChatMessageDto> get(String roomId) {
        return redisTemplate.opsForValue().get(roomId);
    }

    public void delete(String roomId) {
        redisTemplate.delete(roomId);
    }

    public void deleteAll() {
        redisTemplate.discard();
    }
}
