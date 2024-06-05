package com.gunb0s.rt_chat_translation.chatMessage.repository;

import com.gunb0s.rt_chat_translation.chatMessage.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RedisChatMessageCache {
    private final RedisTemplate<String, List<ChatMessage>> redisTemplate;

    @Value("${spring.data.redis.expire}")
    private int expire;

    public void put(String roomId, Queue<ChatMessage> chatMessages) {
        redisTemplate.opsForValue().set(roomId, new LinkedList<>(chatMessages));
        redisTemplate.expire(roomId, expire, TimeUnit.MINUTES);
    }

    public boolean containsKey(String roomId) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(roomId));
    }

    public List<ChatMessage> get(String roomId) {
        return redisTemplate.opsForValue().get(roomId);
    }

    public void delete(String roomId) {
        redisTemplate.delete(roomId);
    }

    public void deleteAll() {
        redisTemplate.discard();
    }
}
